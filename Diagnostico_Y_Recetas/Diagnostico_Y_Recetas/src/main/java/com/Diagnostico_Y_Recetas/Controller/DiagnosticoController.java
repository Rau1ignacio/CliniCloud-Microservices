package com.Diagnostico_Y_Recetas.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Diagnostico_Y_Recetas.Model.Diagnostico;
import com.Diagnostico_Y_Recetas.Services.DiagnosticoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/diagnosticos")
// la url es http://localhost:8080/api/diagnosticos
@Service 
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;


    // Método de prueba para verificar la conexión a la API
    // GET http://localhost:8080/api/diagnosticos/pruebaDeConexion
    @GetMapping("/pruebaDeConexion")// Prueba de conexión
    public String prueba() {
        return """
               <html>
               
               <body style="font-family: Arial, sans-serif; text-align: center; margin-top: 50px;">
               
                    <h1 style="color: green;">Conexión exitosa a la API de Diagnósticos</h1>
                    <p style="color: gray; font-size: 18px;">¡Bienvenido a la API de Diagnósticos de CliniCloud!</p>
                    <p style="color: blue; font-size: 16px;">Estamos listos para ayudarte a gestionar tus diagnósticos.</p>
               
                </body>

               </html>
               """;
    }

    // Método para obtener todos los diagnósticos
    // Los devuelve en formato JSON
    @GetMapping
    public List<Diagnostico> obtenerDiagnosticos() {
        return diagnosticoService.getDiagnosticos();
    }

    // Método para crear un agregar un diagnóstico nuevo
    @PostMapping
    public Diagnostico guardarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoService.getGuardarDiagnostico(diagnostico);
    }
    

    // Método para obtener todos los diagnósticos
    @GetMapping("/todos")
    public List<Diagnostico> obtenerTodosLosDiagnosticos() {
        return diagnosticoService.getDiagnosticos();
    }
    
    
    // Método para actualizar un diagnóstico existente


    // Método para eliminar un diagnóstico por ID
    @PostMapping("/eliminar/{id}")
    public void eliminarDiagnostico(String id) {
        diagnosticoService.deleteDiagnostico(id);
    }
    
    // Método para obtener un diagnóstico por ID
    @GetMapping("/{id}")
    public Diagnostico obtenerDiagnosticoPorId(@RequestParam String id) {
        return diagnosticoService.getDiagnosticoPorId(id);
    }

    // Método para buscar diagnósticos por paciente
    
    // Método para buscar diagnósticos por médico
    
    // Método para buscar diagnósticos por estado
    
    // Método para buscar diagnósticos por tipo
    
    // Método para buscar diagnósticos por fecha
    
    // Método para buscar diagnósticos por rango de fechas
    
    // Método para buscar diagnósticos por síntomas
    
    // Método para buscar diagnósticos por medicamentos
    
    // Método para buscar diagnósticos por recomendaciones



}
