package com.innovasolutions.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.innovasolutions.ds.*")
public class DsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsApplication.class, args);
    }

}
