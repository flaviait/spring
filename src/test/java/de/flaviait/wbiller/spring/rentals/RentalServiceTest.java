package de.flaviait.wbiller.spring.rentals;

import de.flaviait.wbiller.spring.MainConfiguration;
import de.flaviait.wbiller.spring.vehicles.Vehicle;
import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * @author wbiller
 */
@ContextConfiguration(classes = MainConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class
})
public class RentalServiceTest {

  @Autowired
  private JdbcTemplate template;

  @Autowired
  private RentalService rentalService;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Test
  public void rent() {
    fail();
  }

  @Test
  public void rentForAWeek() {
    fail();
  }

  @Test
  public void rentForAWeek_VehicleAlreadyRent() {

    Vehicle vehicle = vehicleRepository.findOne(3).get();

    try {
      rentalService.rentForAWeek(vehicle, Date.valueOf("2015-09-01"));
    } catch (Exception ex) { }

    int count = template.queryForObject("SELECT COUNT(*) FROM rentals WHERE vehicle_id = ?", Integer.class, 3);
    assertEquals(1, count);
  }
}
