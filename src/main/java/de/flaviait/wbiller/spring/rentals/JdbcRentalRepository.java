package de.flaviait.wbiller.spring.rentals;

import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author wbiller
 */
@Repository
class JdbcRentalRepository implements RentalRepository {

  private final JdbcTemplate template;
  private final VehicleRepository vehicleRepository;

  @Autowired
  public JdbcRentalRepository(JdbcTemplate template, VehicleRepository vehicleRepository) {
    this.template = template;
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public Optional<Rental> findOne(Integer id) {
    try {
      return Optional.of(
          template.queryForObject("SELECT id, vehicle_id, date FROM rentals WHERE id = ?",
                                  (rs, rowNum) -> {
                                    return new Rental(rs.getInt("id"),
                                                      vehicleRepository.findOne(rs.getInt("vehicle_id")).get(),
                                                      rs.getDate("rental_date"));
                                  }, id)
      );
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public List<Rental> findAll() {
    return template.query("SELECT id, vehicle_id, date FROM rentals",
                          (rs, rowNum) -> {
                            return new Rental(rs.getInt("id"),
                                              vehicleRepository.findOne(rs.getInt("vehicle_id")).get(),
                                              rs.getDate("rental_date"));
                          });
  }

  @Override
  public Rental save(Rental rental) {
    return rental.isNew() ? insert(rental) : update(rental);
  }

  @Override
  public void delete(Rental rental) {
    template.update("DELETE FROM rentals WHERE id = ?", rental.getId());
  }

  private Rental insert(Rental rental) {
    SimpleJdbcInsert insert = new SimpleJdbcInsert(template).withTableName("rentals")
                                                            .usingGeneratedKeyColumns("id");

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("vehicle_id", rental.getVehicle().getId())
        .addValue("rental_date", rental.getDate());

    Number id = insert.executeAndReturnKey(parameters);
    rental.setId(id.intValue());

    return rental;
  }

  private Rental update(Rental rental) {
    template.update("UPDATE rentals SET vehicle_id = ?, rental_date = ? WHERE id = ?",
                    rental.getVehicle().getId(), rental.getDate(), rental.getId());

    return rental;
  }
}
