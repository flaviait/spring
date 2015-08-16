package de.flaviait.wbiller.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author wbiller
 */
public class ApplicationServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {
        MainConfiguration.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[0];
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/*" };
  }
}
