package de.flaviait.wbiller.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

/**
 * @author wbiller
 */
@Configuration
@ComponentScan("de.flaviait.wbiller.spring")
public class MainConfiguration {

  @Bean
  EmbeddedDatabase embeddedDatabaseBuilder() {
    return new EmbeddedDatabaseBuilder().generateUniqueName(true)
                                        .addDefaultScripts()
                                        .continueOnError(true).build();
  }

  @Bean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
