package com.ambitoscala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ComponentScan(lazyInit = true)
@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class TarantulaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TarantulaApiApplication.class, args);
    }

}
