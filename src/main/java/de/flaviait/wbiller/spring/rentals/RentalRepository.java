package de.flaviait.wbiller.spring.rentals;

import java.util.List;
import java.util.Optional;

/**
 * @author wbiller
 */
public interface RentalRepository {

  Optional<Rental> findOne(Integer id);
  List<Rental> findAll();
  Rental save(Rental vehicle);
  void delete(Rental vehicle);
}
