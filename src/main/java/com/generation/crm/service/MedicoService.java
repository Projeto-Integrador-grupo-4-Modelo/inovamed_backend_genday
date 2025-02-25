package com.generation.crm.service;

import com.generation.crm.model.Medico;
import com.generation.crm.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;


    @Cacheable(value = "medicos", key = "'all'")
    public List<Medico> getMedicos() {
        return repository.findAllLogic();
    }

    @Cacheable(value = "medicos", key = "#id")
    public Medico getMedico(Long id) {
        return repository.findByIdLogic(id).orElseThrow(() -> new NoSuchElementException("Médico não encontrado"));
    }
    @Cacheable(value = "medicos", key = "#especialidade")
    public List<Medico> getMedicosPorEspecialidade(String especialidade) {
        List<Medico> medicos = repository.findByEspecialidadeIgnoreCaseLogic(especialidade);
        if (medicos.isEmpty()) {
            throw new NoSuchElementException("Nenhum médico não encontrado");
        }
        return medicos;
    }
}
