package de.flaviait.wbiller.spring.vehicles;

import java.util.List;
import java.util.Optional;

/**
 * @author wbiller
 */
public interface VehicleRepository {

  Optional<Vehicle> findOne(Integer id);
  List<Vehicle>  findAll();
  Vehicle save(Vehicle vehicle);
  void delete(Vehicle vehicle);
}
