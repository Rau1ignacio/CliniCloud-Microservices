package com.Usuarios.Usuarios.controller;

import com.Usuarios.Usuarios.model.Medico;
import com.Usuarios.Usuarios.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medico")

public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        List<Medico> medicos  = medicoService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(medicos);
        
    }

    @PostMapping
    public ResponseEntity<Medico> guardar(@RequestBody Medico medicos) {
        Medico productoMuevo = medicoService.save(medicos);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoMuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Integer id) {
        try {
            Medico medico = medicoService.findById(id);
            return ResponseEntity.ok(medico);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable Integer id, @RequestBody Medico medico) {
        try {
            Medico pac = medicoService.findById (id) ;
            pac.setId(id);
            pac.setRun(medico.getRun());
            pac.setNombres(medico.getNombres());
            pac.setApellidos(medico.getApellidos());
            pac.setFechaNacimiento(medico.getFechaNacimiento());
            pac.setCorreo(medico.getCorreo());
            pac.setFono(medico.getFono());

            medicoService.save(pac);
            return ResponseEntity.ok(medico);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            medicoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
}
