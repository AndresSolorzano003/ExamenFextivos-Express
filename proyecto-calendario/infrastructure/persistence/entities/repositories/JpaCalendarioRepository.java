package com.pascualbravo.infrastructure.persistence.repositories;

import com.pascualbravo.infrastructure.persistence.entities.CalendarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCalendarioRepository extends JpaRepository<CalendarioEntity, Integer> {
    // Aquí puedes añadir métodos para buscar por año si lo necesitas
}