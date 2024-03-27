package org.multimes.backend.core.web.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.multimes")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public JdbcOperations tasksJdbcOperations(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
