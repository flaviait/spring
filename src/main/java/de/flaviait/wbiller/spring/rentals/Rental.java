package de.flaviait.wbiller.spring.rentals;

import de.flaviait.wbiller.spring.vehicles.Vehicle;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author wbiller
 */
public class Rental {

  private Integer id;
  private final Vehicle vehicle;
  private final Date date;

  Rental(Integer id, Vehicle vehicle, Date date) {
    this.id = id;
    this.vehicle = vehicle;
    this.date = date;
  }

  public Rental(Vehicle vehicle, Date date) {

    Assert.notNull(vehicle);
    Assert.notNull(date);

    this.vehicle = vehicle;
    this.date = date;
  }

  public Integer getId() {
    return id;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public Date getDate() {
    return date;
  }

  void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return String.format("%s %s", DateFormat.getDateInstance(DateFormat.MEDIUM).format(date),
                                  vehicle);
  }

  public boolean isNew() {
    return id == null;
  }
}
