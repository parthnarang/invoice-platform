package com.billt.core.merchantpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.billt.core.merchantpanel"})
@EntityScan(basePackages = {"com.billt.core.merchantpanel"})
@ComponentScan(basePackages = {"com.billt.core.merchantpanel"})
@EnableAsync
public class MerchantpanelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantpanelApplication.class, args);
    }

}
