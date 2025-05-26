package Diagnostico_CliniCloud.Diagnostico.Model;

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
@Table(name = "diagnosticos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {


    @Column(unique = true, length = 10, nullable = false) // RUN del paciente, debe ser único y no nulo
    private String run;                 // RUN del paciente 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;                     // ID único del diagnóstico
    
    @Column
    private Date fecha;                 // Fecha en que se realizó el diagnóstico

    @Column(nullable = false)
    private String correo;              // Correo electrónico del paciente
    
    @Column(nullable = false)
    private String nombre;              // Nombre del paciente

    @Column(nullable = false)
    private String descripcion;         // Descripción detallada del diagnóstico
    
    @Column(nullable = false)
    private String sintomas;            // Síntomas reportados por el paciente 

    @Column(nullable = false)
    private String medicamentos;        // Medicamentos recetados 
    
    @Column(nullable = false)
    private String recomendaciones;     // Recomendaciones adicionales o instrucciones
    
    @Column(nullable = false)
    private String observaciones;       // Observaciones adicionales del médico
    
    @Column(nullable = false)
    private String estado;              // Estado del diagnóstico (ej. "Pendiente", "Completo", "En Progreso")
    
    @Column(nullable = false)
    private String tipo;                // Tipo de diagnóstico (ej. "Consulta", "Urgencia", "Seguimiento")
    
}