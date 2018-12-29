package com.billt.core.invoicereceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.billt.core"})
@EntityScan(basePackages = {"com.billt.core"})
@ComponentScan(basePackages = {"com.billt.core"})
public class InvoiceReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceReceiverApplication.class, args);
    }
}
