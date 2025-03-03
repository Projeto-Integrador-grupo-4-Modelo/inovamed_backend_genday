package com.generation.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.crm.model.Consulta;
import org.springframework.transaction.annotation.Transactional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query(value = "SELECT * FROM tb_consultas WHERE especialidade LIKE %:especialidade% AND deleted = FALSE", nativeQuery = true)
    public List<Consulta> findAllByEspecialidade(@Param("especialidade") String especialidade);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_consultas SET deleted = TRUE WHERE paciente_id=:id", nativeQuery = true)
    void deleteLogicFromUser(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_consultas SET deleted = FALSE WHERE paciente_id=:id", nativeQuery = true)
    void restoreFromUser(Long id);

    @Query(value = "SELECT * FROM tb_consultas WHERE deleted = FALSE", nativeQuery = true)
    List<Consulta> findAllLogic();

    @Query(value = "SELECT * FROM tb_consultas WHERE deleted = FALSE AND id = :id", nativeQuery = true)
    Optional<Consulta> findByIdLogic(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_consultas SET deleted = TRUE WHERE id=:id", nativeQuery = true)
    void deleteLogic(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_paciente SET deleted = FALSE WHERE id=:id", nativeQuery = true)
    void restore(Long id);


}
