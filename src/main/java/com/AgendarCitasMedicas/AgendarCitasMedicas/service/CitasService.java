package com.AgendarCitasMedicas.AgendarCitasMedicas.service;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import com.AgendarCitasMedicas.AgendarCitasMedicas.repository.CitasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CitasService {

    public CitasService(CitasRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    private CitasRepository citasRepository;

    public List<CitasMedica> mostrarCitasMedicas() {
        return citasRepository.findAll();
    }

    public CitasMedica buscarCitaPorId(Integer id_cita) {
        return citasRepository.findById(id_cita)
                .orElseThrow(() -> new RuntimeException("No se encontró la cita con id: " + id_cita));
    }

    public CitasMedica findByRut(String rut) {
        return citasRepository.findByRut(rut);
                new RuntimeException("No se pudo encontrar la cita por rut: " + rut);
    };

    public CitasMedica guardarCita(CitasMedica cita) {
        return citasRepository.save(cita);
    }

    public void eliminarCita(Integer id_cita) {
        citasRepository.deleteById(id_cita);
    }

    public CitasMedica actualizarCita(CitasMedica cita) {
        return citasRepository.save(cita); // save actúa como update si el id ya existe
    }
}
