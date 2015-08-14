package de.flaviait.wbiller.spring.rentals;

import de.flaviait.wbiller.spring.vehicles.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * @author wbiller
 */
public interface RentalService {

  Rental rent(Vehicle vehicle, Date date);

  List<Rental> rentForAWeek(Vehicle vehicle, Date date);
}
