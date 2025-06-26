package Diagnostico_CliniCloud.Diagnostico.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;
import Diagnostico_CliniCloud.Diagnostico.Services.DiagnosticoServices;
import Diagnostico_CliniCloud.Diagnostico.assemblers.DiagnosticoModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/diagnosticos")
@Tag(name = "Diagnostico", description = "Controlador para gestionar diagnósticos en CliniCloud")
public class DiagnosticoControllerV2 {

    @Autowired
    private DiagnosticoServices diagnosticoServices;

    @Autowired
    private DiagnosticoModelAssembler assembler;

    // POST: Guardar un nuevo diagnóstico
    @Operation(summary = "Guardar un nuevo diagnóstico", description = "Permite guardar un nuevo diagnóstico en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Diagnóstico guardado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al guardar el diagnóstico")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Diagnostico>> guardarDiagnostico(@RequestBody Diagnostico diagnostico) {
        Diagnostico nuevoDiagnostico = diagnosticoServices.save(diagnostico);
        return ResponseEntity.status(201).body(assembler.toModel(nuevoDiagnostico));
    }

    // GET: Listar todos los diagnósticos
    @Operation(summary = "Listar todos los diagnósticos", description = "Permite obtener una lista de todos los diagnósticos registrados en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de diagnósticos obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay diagnósticos registrados")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Diagnostico>>> Listar() {
        List<Diagnostico> diagnosticos = diagnosticoServices.listarDiagnosticos();
        if (diagnosticos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Diagnostico>> diagnosticoModels = diagnosticos.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(diagnosticoModels));
    }

    // GET: Buscar diagnóstico por RUN
    @Operation(summary = "Buscar diagnóstico por RUN", description = "Permite buscar un diagnóstico específico utilizando el RUN del paciente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @GetMapping("/buscarPorRun/{run}")
    public ResponseEntity<EntityModel<Diagnostico>> buscarPorRun(@PathVariable String run) {
        Diagnostico diagnostico = diagnosticoServices.buscarPorRun(run);
        if (diagnostico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(diagnostico));
    }

    // GET: Buscar diagnóstico por ID
    @Operation(summary = "Buscar diagnóstico por ID", description = "Permite buscar un diagnóstico específico utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, ID inválido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el diagnóstico, Quizas no existe")
    })
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<EntityModel<Diagnostico>> buscarPorId(@PathVariable int id) {
        Diagnostico diagnostico = diagnosticoServices.buscarPorId(id);
        if (diagnostico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(diagnostico));
    }

    // PUT: Actualizar diagnóstico por ID
    @Operation(summary = "Actualizar diagnóstico por ID", description = "Permite actualizar un diagnóstico existente utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Diagnóstico actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EntityModel<Diagnostico>> actualizarDiagnostico(@RequestBody Diagnostico diagnostico, @PathVariable int id) {
        try {
            Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorId(id);
            if (diagnosticoExistente == null) {
                return ResponseEntity.notFound().build();
            }
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

            Diagnostico actualizado = diagnosticoServices.save(diagnosticoExistente);
            return ResponseEntity.ok(assembler.toModel(actualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // POST: Eliminar diagnóstico por ID
    @Operation(summary = "Eliminar diagnóstico por ID", description = "Permite eliminar un diagnóstico específico utilizando su ID único")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Diagnóstico eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ID del diagnóstico a eliminar")
    @Schema(description = "ID del diagnóstico a eliminar", example = "1")
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDiagnostico(@PathVariable int id) {
        Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorId(id);
        if (diagnosticoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        diagnosticoServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    // POST: Eliminar diagnóstico por RUN
    @Operation(summary = "Eliminar diagnóstico por RUN", description = "Permite eliminar un diagnóstico específico utilizando el RUN del paciente")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Diagnóstico eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Diagnóstico no encontrado")
    })
    @PostMapping("/eliminarPorRun/{run}")
    public ResponseEntity<Void> eliminarPorRun(@PathVariable String run) {
        Diagnostico diagnosticoExistente = diagnosticoServices.buscarPorRun(run);
        if (diagnosticoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        diagnosticoServices.delete(diagnosticoExistente.getId());
        return ResponseEntity.noContent().build();
    }
}
