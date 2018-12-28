package com.billt.core.datasourcebase;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan({"com.billt.core.datasourcebase.repositories.jpa.read", "com.billt.core.datasourcebase.entities.jpa"})
@Configuration
public class DataSourceBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceBaseApplication.class, args);
    }
}
