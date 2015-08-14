package de.flaviait.wbiller.spring.vehicles;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wbiller
 */
public class Vehicle implements Serializable {

  private Integer id;
  private final String manufacturer;
  private final String model;

  public Vehicle(String manufacturer, String model) {
    Assert.hasText(manufacturer);
    Assert.hasText(model);

    this.manufacturer = manufacturer;
    this.model = model;
  }

  Vehicle(Integer id, String mancufacturer, String model) {
    this.id = id;
    this.manufacturer = mancufacturer;
    this.model = model;
  }


  public Integer getId() {
    return id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getModel() {
    return model;
  }

  void setId(Integer id) {
    this.id = id;
  }


  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, model);
  }

  @Override
  public boolean equals(Object obj) {
    if(null == obj) return false;
    if(this == obj) return true;
    if(getClass() != obj.getClass()) return false;

    Vehicle other = (Vehicle) obj;
    return Objects.equals(manufacturer, other.manufacturer) &&
           Objects.equals(model, other.model);
  }

  @Override
  public String toString() {
    return String.format("%s %s", manufacturer, model);
  }

  public boolean isNew() {
    return id == null;
  }
}
