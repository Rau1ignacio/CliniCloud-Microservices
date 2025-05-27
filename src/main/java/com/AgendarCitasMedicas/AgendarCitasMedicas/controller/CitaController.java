package com.AgendarCitasMedicas.AgendarCitasMedicas.controller;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import com.AgendarCitasMedicas.AgendarCitasMedicas.service.CitasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitasService citasService;

    public CitaController(CitasService citasService) {
        this.citasService = citasService;
    }

    @GetMapping
    public ResponseEntity<List<CitasMedica>> listar() {
        List<CitasMedica> citasMedicas = citasService.mostrarCitasMedicas();
        if (citasMedicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citasMedicas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CitasMedica> buscarPorId(@PathVariable Integer id) {
        try {
            CitasMedica cita = citasService.buscarCitaPorId(id);
            return ResponseEntity.ok(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity<CitasMedica> buscarPorRut(@PathVariable String rut) {
        Optional<CitasMedica> cita = citasService.buscarPorRut(rut);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitasMedica> crear(@RequestBody CitasMedica nuevaCita) {
        CitasMedica citaGuardada = citasService.guardarCita(nuevaCita);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitasMedica> actualizar(@PathVariable Integer id, @RequestBody CitasMedica datosActualizados) {
        try {
            CitasMedica citaActualizada = citasService.actualizarCita(id, datosActualizados);
            return ResponseEntity.ok(citaActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            citasService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
