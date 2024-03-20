package org.multimes.backend.core.web.config;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    @FlywayDataSource
    @ConfigurationProperties(prefix = "spring.datasource.multimes")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
