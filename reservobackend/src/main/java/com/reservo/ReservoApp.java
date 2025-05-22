package com.reservo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.reservo.Repository")
@EntityScan(basePackages = "com.reservo.Models")
public class ReservoApp {

    public static void main( String[] args ) {
        SpringApplication.run(ReservoApp.class, args);
    }

}
