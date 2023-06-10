package com.agil.consultas.apiconsultaagil.entities.pk;

import com.agil.consultas.apiconsultaagil.entities.ConsultaEntity;
import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity(name = "paciente_consulta")
public class PacienteConsultaPK {

    @Id
    @Column(name = "id_paciente_consulta")
    private Long idPacienteConsulta;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private PacienteEntity pacienteEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consulta")
    private ConsultaEntity consultaEntity;
}
