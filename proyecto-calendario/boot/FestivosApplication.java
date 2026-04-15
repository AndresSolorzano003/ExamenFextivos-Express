package com.pascualbravo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pascualbravo") // Para que encuentre los beans en todos los módulos
public class FestivosApplication {
    public static void main(String[] args) {
        SpringApplication.run(FestivosApplication.class, args);
    }
}