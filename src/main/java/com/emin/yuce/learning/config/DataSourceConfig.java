package com.emin.yuce.learning.config;

import com.emin.yuce.learning.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dsTest")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTest")
    @Autowired
    public JdbcTemplate testJdbcTemplate(@Qualifier("dsTest") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }

    @Bean(name = "dsDev")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource devDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcDev")
    @Autowired
    public JdbcTemplate devJdbcTemplate(@Qualifier("dsDev") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }

    @Bean(name = "fileService")
    @Autowired
    public FileService fileService() {
        FileService fileService = new FileService();
        fileService.parseFile();
        return fileService;
    }
}
