package com.Diagnostico_Y_Recetas.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;  
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "diagnostico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {

    private String run;                 // RUN del paciente (Registro Único Nacional)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                     // ID único del diagnóstico
    private String descripcion;         // Descripción detallada del diagnóstico
    
    @Column
    private Date fecha;                 // Fecha en que se realizó el diagnóstico
    
    @Column(length = 500)
    private String sintomas;            // Síntomas reportados por el paciente (como texto separado por comas)

    @Column(length = 500)
    private String medicamentos;        // Medicamentos recetados (como texto separado por comas)
    
    @Column(length = 500)
    private String recomendaciones;     // Recomendaciones adicionales o instrucciones
    
    @Column(length = 500)
    private String observaciones;       // Observaciones adicionales del médico
    
    @Column(length = 50)
    private String estado;              // Estado del diagnóstico (ej. "Pendiente", "Completo", "En Progreso")
    
    @Column(length = 50)
    private String tipo;                // Tipo de diagnóstico (ej. "Consulta", "Urgencia", "Seguimiento")
    
}
