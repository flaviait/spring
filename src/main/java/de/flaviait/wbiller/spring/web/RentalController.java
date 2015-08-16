package de.flaviait.wbiller.spring.web;

import de.flaviait.wbiller.spring.rentals.Rental;
import de.flaviait.wbiller.spring.rentals.RentalRepository;
import de.flaviait.wbiller.spring.rentals.RentalService;
import de.flaviait.wbiller.spring.vehicles.Vehicle;
import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author wbiller
 */
@Controller
@RequestMapping("/")
class RentalController {

  private final RentalRepository rentalRepository;
  private final RentalService rentalService;
  private final VehicleRepository vehicleRepository;

  @Autowired
  RentalController(RentalRepository rentalRepository, RentalService rentalService,
                   VehicleRepository vehicleRepository) {
    this.rentalRepository = rentalRepository;
    this.rentalService = rentalService;
    this.vehicleRepository = vehicleRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String get() {
    return "rentals";
  }

  @ModelAttribute("rentals")
  public List<Rental> getRentals() {
    return rentalRepository.findAll();
  }

  @ModelAttribute("vehicles")
  public List<Vehicle> getVehicles() {
    return vehicleRepository.findAll();
  }
}
