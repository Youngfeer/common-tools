package com.yfrao.tools.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "orderDataSource")
    @Qualifier("orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderJdbcTemplate")
    public JdbcTemplate orderJdbcTemplate(
            @Qualifier("orderDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mesfDataSource")
    @Qualifier("mesfDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mesf")
    public DataSource mesfDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mesfJdbcTemplate")
    public JdbcTemplate mesfJdbcTemplate(
            @Qualifier("mesfDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
