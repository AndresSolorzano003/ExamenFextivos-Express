package com.pascualbravo.config;

import com.pascualbravo.application.ValidarFechaUseCase;
import com.pascualbravo.domain.ports.CalendarioPersistencePort;
import com.pascualbravo.domain.ports.FestivoServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ValidarFechaUseCase validarFechaUseCase(
            CalendarioPersistencePort persistence, 
            FestivoServicePort service) {
        return new ValidarFechaUseCase(persistence, service);
    }
}