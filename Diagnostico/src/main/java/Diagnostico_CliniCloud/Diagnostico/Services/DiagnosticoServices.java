package Diagnostico_CliniCloud.Diagnostico.Services;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;
import Diagnostico_CliniCloud.Diagnostico.Repository.DiagnosticoRepository;

@Service
@Transactional
public class DiagnosticoServices {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    // Lista todos los diagnósticos en la base de datos
    public List<Diagnostico> listarDiagnosticos() {
        return diagnosticoRepository.findAll();
    }

    // Busca un diagnóstico por el RUN del paciente
    public Diagnostico buscarPorRun(String run) {
        return diagnosticoRepository.buscarPorRun(run);
    }

    // Busca un diagnóstico por su ID único
    public Diagnostico buscarPorId(int id) {
        return diagnosticoRepository.findById(id).get();
    }

    // Guarda un nuevo diagnóstico o actualiza uno existente
    public Diagnostico save(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    // Elimina un diagnóstico por su ID
    public void delete(int id) {
        diagnosticoRepository.deleteById(id);
    }
}
