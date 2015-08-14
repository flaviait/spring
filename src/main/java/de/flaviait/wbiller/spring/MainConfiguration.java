package de.flaviait.wbiller.spring;

import de.flaviait.wbiller.spring.vehicles.FakeVehicleRepository;
import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * @author wbiller
 */
@Configuration
class MainConfiguration {

  @Bean
  VehicleRepository vehicleRepository() {
    return new FakeVehicleRepository();
  }

  @Bean
  EmbeddedDatabase embeddedDatabaseBuilder() {
    return new EmbeddedDatabaseBuilder().generateUniqueName(true)
                                        .addDefaultScripts()
                                        .continueOnError(true).build();
  }
}
