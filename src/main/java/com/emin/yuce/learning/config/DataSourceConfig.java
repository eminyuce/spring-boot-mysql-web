package com.emin.yuce.learning.config;

import com.emin.yuce.learning.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean(name = "jdbcDev")
    public JdbcTemplate devJdbcTemplate(DataSource dsMaster) {
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
