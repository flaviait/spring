package de.flaviait.wbiller.spring.rentals;

import de.flaviait.wbiller.spring.vehicles.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wbiller
 */
@Service
class RentalServiceImpl implements RentalService {

  private final RentalRepository rentalRepository;

  @Autowired
  public RentalServiceImpl(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  @Override
  public Rental rent(Vehicle vehicle, Date date) {

    Assert.notNull(vehicle);
    Assert.notNull(date);

    return rentalRepository.save(new Rental(vehicle, date));
  }

  @Override
  public List<Rental> rentForAWeek(Vehicle vehicle, Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    List<Rental> rentals = new ArrayList<>();
    for(int i = 0; i < 6; i++) {
      rentals.add(rent(vehicle, cal.getTime()));
      cal.add(Calendar.DATE, 1);
    }

    return rentals;
  }

}
