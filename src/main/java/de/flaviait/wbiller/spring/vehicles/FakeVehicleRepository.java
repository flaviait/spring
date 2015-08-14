package de.flaviait.wbiller.spring.vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wbiller
 */
public class FakeVehicleRepository implements VehicleRepository {

  private static int counter = 0;
  private final List<Vehicle> store;

  public FakeVehicleRepository() {
    this.store = new ArrayList<Vehicle>();
  }

  @Override
  public Optional<Vehicle> findOne(Integer id) {
    return store.stream().filter(p -> p.getId().equals(id)).findFirst();
  }

  @Override
  public List<Vehicle> findAll() {
    return new ArrayList<>(store);
  }

  @Override
  public Vehicle save(Vehicle vehicle) {
    vehicle.setId(++counter);
    store.add(vehicle);
    return vehicle;
  }

  @Override
  public void delete(Vehicle vehicle) {
    store.remove(vehicle);
  }
}
