package com.AgendarCitasMedicas.AgendarCitasMedicas.controller;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import com.AgendarCitasMedicas.AgendarCitasMedicas.service.CitasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citas")
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
}
