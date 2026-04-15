package com.pascualbravo.infrastructure.persistence;

import com.pascualbravo.domain.Calendario;
import com.pascualbravo.domain.ports.CalendarioPersistencePort;
import org.springframework.stereotype.Component;

@Component
public class CalendarioPersistenceAdapter implements CalendarioPersistencePort {

    private final JpaCalendarioRepository repository;

    public CalendarioPersistenceAdapter(JpaCalendarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Calendario dominio) {
        CalendarioEntity entity = new CalendarioEntity();
        entity.setFecha(dominio.getFecha());
        entity.setIdTipo(dominio.getIdTipo());
        entity.setDescripcion(dominio.getDescripcion());
        repository.save(entity);
    }
}