package com.Usuarios.Usuarios.service;



import com.Usuarios.Usuarios.model.Medico;
import com.Usuarios.Usuarios.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico findById(long id) {
        return medicoRepository.findById(id).get();
    }

    public Medico save(Medico paciente) {
        return medicoRepository.save(paciente);
    }

    public void delete(Long id) {
        medicoRepository.deleteById(id);
    }
}


