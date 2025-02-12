package com.generation.crm.controller;

import com.generation.crm.model.Medico;
import com.generation.crm.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Operation(summary = "Criar um médico", tags = {"Médicos"}, description = "Salvar um médico no banco")
    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody @Valid Medico medico) {
        repository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @Operation(summary = "Liste todos os médicos", tags = {"Médicos"}, description = "Liste todos os médicos salvos")
    @GetMapping
    public ResponseEntity<List<Medico>> getAll() {
        repository.findAllLogic();
        return ResponseEntity.ok(repository.findAllLogic());
    }

    @Operation(summary = "encontre um médico pelo seu id", tags = {"Médicos"}, description = "Busque um médico pelo seu id enviado como parametro")
    @GetMapping("/{id}")
    public ResponseEntity<Medico> getById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findByIdLogic(id).orElseThrow(() -> new NoSuchElementException("Médico não encontrado")));
    }

    @Operation(summary = "Atualiza um médico", tags = {"Médicos"}, description = "Atualize um médico enviando seu id como parametro")
    @PutMapping("/{id}")
    public ResponseEntity<Medico> put(@PathVariable Long id, @RequestBody @Valid Medico medico) {
        Medico referenceById = repository.getReferenceById(id);
        referenceById.update(medico);
        repository.save(referenceById);
        return ResponseEntity.ok(referenceById);
    }

    @Operation(summary = "Desabilita um médico", tags = {"Médicos"}, description = "Desabilita um médico no banco")
    @DeleteMapping("/{id}")
    public ResponseEntity<Medico> delete(@PathVariable Long id) {
        boolean exists = repository.existsById(id);

        if (!exists) throw new NoSuchElementException("Médico não encontrado");

        repository.deleteLogic(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Habilita um médico", tags = {"Médicos"}, description = "Habilita um médico no banco")
    @PutMapping("/restaurar/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        repository.restore(id);

        return ResponseEntity.noContent().build();
    }
}
