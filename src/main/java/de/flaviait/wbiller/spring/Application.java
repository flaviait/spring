package de.flaviait.wbiller.spring;

import de.flaviait.wbiller.spring.vehicles.Vehicle;
import de.flaviait.wbiller.spring.vehicles.VehicleRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wbiller
 */
class Application {

  public static void main(String... args) {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(Application.class.getPackage().getName());

    VehicleRepository repository = context.getBean(VehicleRepository.class);
    Vehicle vehicle = repository.save(new Vehicle("Volkswagen", "Tiguan"));

    System.out.println(vehicle.toString());
  }
}
