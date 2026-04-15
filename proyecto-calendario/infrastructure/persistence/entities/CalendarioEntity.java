package com.pascualbravo.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendario")
public class CalendarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "idtipo", nullable = false)
    private Integer idTipo; // 1: Laboral, 2: Fin de Semana, 3: Festivo

    @Column(length = 100)
    private String descripcion;

    // Constructores, Getters y Setters
}