package com.AgendarCitasMedicas.AgendarCitasMedicas.service;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import com.AgendarCitasMedicas.AgendarCitasMedicas.repository.CitasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<CitasMedica> buscarPorRut(String rut) {
        return citasRepository.findByRut(rut);
    }

    public CitasMedica guardarCita(CitasMedica cita) {
        return citasRepository.save(cita);
    }

    public void eliminarCita(Integer id_cita) {
        if (!citasRepository.existsById(id_cita)) {
            throw new RuntimeException("Cita no encontrada con id: " + id_cita);
        }
        citasRepository.deleteById(id_cita);
    }

    public CitasMedica actualizarCita(Integer id, CitasMedica datosActualizados) {
        CitasMedica citaExistente = buscarCitaPorId(id);

        citaExistente.setRut(datosActualizados.getRut());
        citaExistente.setNombre_paciente(datosActualizados.getNombre_paciente());
        citaExistente.setApellido_paciente(datosActualizados.getApellido_paciente());
        citaExistente.setCorreo_paciente(datosActualizados.getCorreo_paciente());
        citaExistente.setTelefono(datosActualizados.getTelefono());
        citaExistente.setNombre_medico(datosActualizados.getNombre_medico());
        citaExistente.setEspecialidad_medico(datosActualizados.getEspecialidad_medico());
        citaExistente.setFecha_hora_cita(datosActualizados.getFecha_hora_cita());
        citaExistente.setDescripcion(datosActualizados.getDescripcion());

        return citasRepository.save(citaExistente);
    }

}
