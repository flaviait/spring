package de.flaviait.wbiller.spring.vehicles;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author wbiller
 */
class JdbcVehicleRepository implements VehicleRepository {

  private final JdbcTemplate template;

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
    return null;
  }

  @Override
  public void delete(Vehicle vehicle) {

  }
}
