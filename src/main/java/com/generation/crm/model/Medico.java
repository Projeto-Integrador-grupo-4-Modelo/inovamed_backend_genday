package com.generation.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_medicos")
public class Medico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String especialidade;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}/[A-Z]{2}", message = "CRM deve seguir o formato NNNNNN/UF")
    private String crm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medico", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("medico")
    private List<Consulta> consulta;

    private boolean deleted = false;

    public Medico() {
    }

    public void update(Medico medico) {
        if (medico.nome != null && !medico.nome.isBlank()) {
            this.nome = medico.nome;
        }
        if (medico.especialidade != null && !medico.especialidade.isBlank()) {
            this.especialidade = medico.getEspecialidade();
        }
        if (medico.crm != null && !medico.crm.isBlank()) {
            this.crm = medico.getCrm();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }


}
