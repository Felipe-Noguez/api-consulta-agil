package com.agil.consultas.apiconsultaagil.entities;

import com.agil.consultas.apiconsultaagil.entities.pk.PacienteConsultaPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @Column(name = "especialidade")
    private String especialidade;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_hora_consulta")
    private LocalDateTime dataHoraConsulta;

    @OneToMany(mappedBy = "consultaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PacienteConsultaPK> pacienteConsultaPK;
}
