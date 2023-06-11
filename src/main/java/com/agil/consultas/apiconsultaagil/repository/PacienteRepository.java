package com.agil.consultas.apiconsultaagil.repository;

import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    public Optional<PacienteEntity> findByTelefone(String telefone);
}
