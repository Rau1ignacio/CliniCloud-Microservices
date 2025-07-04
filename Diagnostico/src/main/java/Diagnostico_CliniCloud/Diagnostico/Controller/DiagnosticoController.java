package Diagnostico_CliniCloud.Diagnostico.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;
import Diagnostico_CliniCloud.Diagnostico.Services.DiagnosticoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/diagnosticos")
// la Url es http://localhost:8080/api/v1/diagnosticos
@Tag(name = "Diagnostico", description = "Controlador para gestionar diagnósticos en CliniCloud")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoServices diagnosticoServices;

    //// PostMapping
    
    // Metodo PostMapping para guardar un nuevo diagnóstico
    @Operation(summary = "Guardar un nuevo diagnóstico", description = "Permite guardar un nuevo diagnóstico en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Diagnóstico guardado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al guardar el diagnóstico")
    })
    @PostMapping // Url: http://localhost:8080/api/v1/diagnosticos
    public ResponseEntity<Diagnostico> guardarDiagnostico(@RequestBody Diagnostico diagnostico) {
        // Llama al servicio para guardar el diagnóstico
        Diagnostico nuevoDiagnostico = diagnosticoServices.save(diagnostico);
        // Retorna el diagnóstico guardado con un código de estado 201 (Creado)
        return ResponseEntity.status(201).body(nuevoDiagnostico);
    }



    //// GetMapping
    
    // Metodo GetMapping para listar todos los diagnósticos
    @Operation(summary = "Listar todos los diagnósticos", description = "Permite obtener una lista de todos los diagnósticos registrados en el sistema")    
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de diagnósticos obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay diagnósticos registrados")
    })
    @GetMapping // Url: http://localhost:8080/api/v1/diagnosticos
    public ResponseEntity<List<Diagnostico>> Listar() {
        // Llama al servicio para obtener la lista de diagnósticos
        List<Diagnostico> diagnosticos = diagnosticoServices.listarDiagnosticos();
        // Verifica si la lista está vacía
        if (diagnosticos.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(diagnosticos);
    }

    // Metodo GetMapping para buscar un diagnóstico por RUN
    @Operation(summary = "Buscar diagnóstico por RUN", description = "Permite buscar un diagnóstico específico utilizando el RUN del paciente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @GetMapping("/buscarPorRun/{run}") // Url: http://localhost:8080/api/v1/diagnosticos/buscarPorRun/
    public ResponseEntity<Diagnostico> buscarPorRun(@PathVariable String run) {
        // Llama al servicio para buscar el diagnóstico por RUN
        Diagnostico diagnostico = diagnosticoServices.buscarPorRun(run);
        // Verifica si se encontró el diagnóstico
        if (diagnostico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diagnostico);
    }

    // Metodo GetMapping para buscar un diagnóstico por ID
    @Operation(summary = "Buscar diagnóstico por ID", description = "Permite buscar un diagnóstico específico utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, ID inválido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el diagnóstico, Quizas no existe")
    })
    @GetMapping("/buscarPorId/{id}") // Url: http://localhost:8080/api/v1/diagnosticos/buscarPorId/
    public ResponseEntity<Diagnostico> buscarPorId(@PathVariable int id) {
        // Llama al servicio para buscar el diagnóstico por ID
        Diagnostico diagnostico = diagnosticoServices.buscarPorId(id);
        // Verifica si se encontró el diagnóstico
        if (diagnostico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diagnostico);
    }
    


    //// PutMapping
    
    // Metodo PutMapping para actualizar un diagnóstico existente con ID
    @Operation(summary = "Actualizar diagnóstico por ID", description = "Permite actualizar un diagnóstico existente utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @PutMapping("/actualizar/{id}") 
    public ResponseEntity<Diagnostico> actualizarDiagnostico(@RequestBody Diagnostico diagnostico, @PathVariable int id) {
        // Verifica si el diagnóstico existe
        try {
            Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorId(id);
            if (diagnosticoExistente == null) {
                return ResponseEntity.notFound().build();
            }
            // Actualiza los campos del diagnóstico existente
            diagnosticoExistente.setRun(diagnostico.getRun());
            diagnosticoExistente.setFecha(diagnostico.getFecha());
            diagnosticoExistente.setCorreo(diagnostico.getCorreo());
            diagnosticoExistente.setNombre(diagnostico.getNombre());
            diagnosticoExistente.setDescripcion(diagnostico.getDescripcion());
            diagnosticoExistente.setSintomas(diagnostico.getSintomas());
            diagnosticoExistente.setMedicamentos(diagnostico.getMedicamentos());
            diagnosticoExistente.setRecomendaciones(diagnostico.getRecomendaciones());
            diagnosticoExistente.setObservaciones(diagnostico.getObservaciones());
            diagnosticoExistente.setEstado(diagnostico.getEstado());
            diagnosticoExistente.setTipo(diagnostico.getTipo());

            // Guarda el diagnóstico actualizado
            Diagnostico actualizado = diagnosticoServices.save(diagnosticoExistente);
            
            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    //// DeleteMapping
    
    // Metodo DeleteMapping para eliminar un diagnóstico por ID
    @Operation(summary = "Eliminar diagnóstico por ID", description = "Permite eliminar un diagnóstico específico utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Diagnóstico eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ID del diagnóstico a eliminar")
    @Schema (description = "ID del diagnóstico a eliminar", example = "1")
    @PostMapping("/eliminar/{id}") // 
    public ResponseEntity<Void> eliminarDiagnostico(@PathVariable int id) {
        // Verifica si el diagnóstico existe
        Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorId(id);
        if (diagnosticoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        // Elimina el diagnóstico
        diagnosticoServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Metodo DeleteMapping para eliminar todos los diagnósticos de un RUN específico
    @Operation(summary = "Eliminar diagnóstico por RUN", description = "Permite eliminar un diagnóstico específico utilizando el RUN del paciente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Diagnóstico eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @PostMapping("/eliminarPorRun/{run}") // Url: http://localhost:8080/api/v1/diagnosticos/eliminarPorRun/
    public ResponseEntity<Void> eliminarPorRun(@PathVariable String run) {
        // Busca el diagnóstico por RUN
        Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorRun(run);
        if (diagnosticoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        // Elimina el diagnóstico
        diagnosticoServices.delete(diagnosticoExistente.getId());
        return ResponseEntity.noContent().build();
    }


}
