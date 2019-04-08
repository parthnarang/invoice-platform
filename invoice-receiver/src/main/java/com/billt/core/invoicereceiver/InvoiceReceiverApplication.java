package com.billt.core.invoicereceiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.billt.core"})
@EnableMongoRepositories(basePackages = {"com.billt.core"})
@EntityScan(basePackages = {"com.billt.core"})
@ComponentScan(basePackages = {"com.billt.core"})
@EnableAsync
public class InvoiceReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceReceiverApplication.class, args);
        //ApplicationContext context = new ClassPathXmlApplicationContext("TemplateEngineApplicationContext.xml");
    }
}
