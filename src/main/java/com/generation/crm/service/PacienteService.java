package com.generation.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.crm.model.Paciente;
import com.generation.crm.repository.PacienteRepository;

import java.util.NoSuchElementException;


@Service
	public class PacienteService {

	    @Autowired
	    private PacienteRepository pacienteRepository;

	    public Paciente verificarConvenio(Long pacienteId) {

	        return pacienteRepository.verificarConvenio(pacienteId)
	                .orElseThrow(() -> new NoSuchElementException("Paciente não encontrado"));


	    }

} 

