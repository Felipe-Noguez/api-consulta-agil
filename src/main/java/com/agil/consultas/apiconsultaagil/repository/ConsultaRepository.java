package com.agil.consultas.apiconsultaagil.repository;

import com.agil.consultas.apiconsultaagil.entities.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    Optional<ConsultaEntity> findByDataHoraConsulta(LocalDateTime consulta);
}
