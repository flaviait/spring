package de.flaviait.wbiller.spring.vehicles;

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
class JdbcVehicleRepository implements VehicleRepository {

  private final JdbcTemplate template;

  @Autowired
  private JdbcVehicleRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Optional<Vehicle> findOne(Integer id) {
    try {
      return Optional.of(
          template.queryForObject("SELECT id, manufacturer, model FROM vehicles WHERE id = ?",
                                  (rs, rowNum) -> { return new Vehicle(rs.getInt("id"), rs.getString("manufacturer"),
                                                                       rs.getString("Model")); },
                                  id)
      );
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public List<Vehicle> findAll() {
    return template.query("SELECT id, manufacturer, model FROM vehicles",
                          (rs, rowNum) -> { return new Vehicle(rs.getInt("id"), rs.getString("manufacturer"),
                                                               rs.getString("Model")); });
  }

  @Override
  public Vehicle save(Vehicle vehicle) {
    return vehicle.isNew() ? insert(vehicle) : update(vehicle);
  }

  @Override
  public void delete(Vehicle vehicle) {
    template.update("DELETE FROM vehicles WHERE id = ?", vehicle.getId());
  }

  private Vehicle insert(Vehicle vehicle) {
    SimpleJdbcInsert insert = new SimpleJdbcInsert(template).withTableName("vehicles")
                                                            .usingGeneratedKeyColumns("id");

    SqlParameterSource parameters = new MapSqlParameterSource()
                                      .addValue("manufacturer", vehicle.getManufacturer())
                                      .addValue("model", vehicle.getModel());

    Number id = insert.executeAndReturnKey(parameters);
    vehicle.setId(id.intValue());

    return vehicle;
  }

  private Vehicle update(Vehicle vehicle) {
    template.update("UPDATE vehicles SET manufacturer = ?, model = ? WHERE id = ?",
                    vehicle.getManufacturer(), vehicle.getModel(), vehicle.getId());

    return vehicle;
  }
}
