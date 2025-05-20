package com.Diagnostico_Y_Recetas.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {

    private String run;                 // RUN del paciente (Registro Único Nacional)
    private String id;                  // ID único del diagnóstico
    private String paciente;            // Nombre completo del paciente
    private String medico;              // Nombre completo del médico que realiza el diagnóstico
    private String descripcion;         // Descripción detallada del diagnóstico
    private Date fecha;            // Fecha en que se realizó el diagnóstico
    private List<String> sintomas;      // Lista de síntomas reportados por el paciente
    private List<String> medicamentos;  // Lista de medicamentos recetados
    private String recomendaciones;     // Recomendaciones adicionales o instrucciones
    private String observaciones;       // Observaciones adicionales del médico
    private String estado;              // Estado del diagnóstico (ej. "Pendiente", "Completo", "En Progreso")
    private String tipo;                // Tipo de diagnóstico (ej. "Consulta", "Urgencia", "Seguimiento")



}
