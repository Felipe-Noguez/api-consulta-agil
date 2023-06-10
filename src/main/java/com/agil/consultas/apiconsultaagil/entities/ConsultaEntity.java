package com.agil.consultas.apiconsultaagil.entities;

import com.agil.consultas.apiconsultaagil.entities.pk.PacienteConsultaPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "consulta")
public class ConsultaEntity {

    @Id
    @Column(name = "id_consulta")
    private Long idConsulta;

    @Column(name = "especialidade")
    private String especialidade;

    private LocalDateTime dataHoraConsulta;

    @OneToMany(mappedBy = "consultaEntity", fetch = FetchType.LAZY)
    private Set<PacienteConsultaPK> pacienteConsultaPK;
}
