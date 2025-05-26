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
    // Aquí puedes agregar métodos para interactuar con el repositorio


    // Metodo para listar todos los diagnosticos
    public List<Diagnostico> listarDiagnosticos() {
        return diagnosticoRepository.findAll();
    }

    // Metodo para buscar un diagnostico por RUN
    public Diagnostico buscarPorRun(String run) {
        return diagnosticoRepository.buscarPorRun(run);
    }

    // Metodo para buscar por ID
    public Diagnostico buscarPorId(int id) {
        return diagnosticoRepository.findById(id).get();
    }

    // Metodo para guardar o actualizar un nuevo diagnóstico
    public Diagnostico save(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    // Método para eliminar un diagnóstico por su ID
    public void delete(int id) {
        diagnosticoRepository.deleteById(id);
    }


}
