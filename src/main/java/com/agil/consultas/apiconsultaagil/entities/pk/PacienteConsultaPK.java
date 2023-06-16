package com.agil.consultas.apiconsultaagil.entities.pk;

import com.agil.consultas.apiconsultaagil.entities.ConsultaEntity;
import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Entity(name = "paciente_consulta")
public class PacienteConsultaPK {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente_consulta")
    private Long idPacienteConsulta;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private PacienteEntity pacienteEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consulta")
    private ConsultaEntity consultaEntity;

//    Adicionado equals e hashcode para evitar a duplicação de registros na tabela intermediária
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PacienteConsultaPK)) {
            return false;
        }
        PacienteConsultaPK that = (PacienteConsultaPK) o;
        return Objects.equals(getPacienteEntity().getIdPaciente(), that.getPacienteEntity().getIdPaciente())
                && Objects.equals(getConsultaEntity().getIdConsulta(), that.getConsultaEntity().getIdConsulta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPacienteEntity().getIdPaciente(), getConsultaEntity().getIdConsulta());
    }
}
