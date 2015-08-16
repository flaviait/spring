package de.flaviait.wbiller.spring;

import de.flaviait.wbiller.spring.web.VehicleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * @author wbiller
 */
@Configuration
@EnableWebMvc
class WebConfiguration extends WebMvcConfigurerAdapter {

  @Autowired
  private VehicleConverter vehicleConverter;

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {

    TemplateResolver resolver = new ClassLoaderTemplateResolver();
    resolver.setPrefix("/templates/");
    resolver.setSuffix(".html");

    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(resolver);

    ThymeleafViewResolver vr = new ThymeleafViewResolver();
    vr.setTemplateEngine(engine);

    registry.viewResolver(vr);
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(vehicleConverter);
  }
}
