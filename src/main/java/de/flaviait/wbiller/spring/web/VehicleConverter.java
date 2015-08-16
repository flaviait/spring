package de.flaviait.wbiller.spring.web;

import de.flaviait.wbiller.spring.vehicles.Vehicle;
import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author wbiller
 */
@Component
public class VehicleConverter implements Converter<String, Vehicle> {

  private final VehicleRepository repository;

  @Autowired
  VehicleConverter(VehicleRepository repository) {
    this.repository = repository;
  }

  @Override
  public Vehicle convert(String source) {
    Integer id = Integer.parseInt(source);
    if(id == null)
      return null;

    return repository.findOne(id).get();
  }
}
