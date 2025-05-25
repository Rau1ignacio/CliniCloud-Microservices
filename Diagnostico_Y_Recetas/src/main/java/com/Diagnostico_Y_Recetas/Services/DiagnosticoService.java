package com.Diagnostico_Y_Recetas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Diagnostico_Y_Recetas.Model.Diagnostico;
import com.Diagnostico_Y_Recetas.Repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticoRepository.obtenerDiagnosticos();
    }

    public Diagnostico getGuardarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.guardarDiagnostico(diagnostico);
    }

    public Diagnostico getDiagnosticoPorId(int id) {
        return diagnosticoRepository.getDiagnosticoPorId(id);
    }

    public Diagnostico updateDiagnostico(Diagnostico nuevoDiagnostico) {
        return diagnosticoRepository.actualizarDiagnostico(nuevoDiagnostico);
    }

    public String deleteDiagnostico(int id) {
        diagnosticoRepository.eliminarDiagnostico(id);
        return "Diagnóstico eliminado con éxito";
    }

}
