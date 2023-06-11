package com.agil.consultas.apiconsultaagil.repository;

import com.agil.consultas.apiconsultaagil.entities.pk.PacienteConsultaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteConsultaRepository extends JpaRepository<PacienteConsultaPK, Long> {

}
