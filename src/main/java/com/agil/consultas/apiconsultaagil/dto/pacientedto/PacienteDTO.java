package com.agil.consultas.apiconsultaagil.dto.pacientedto;

import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO {

    private Long idPaciente;

    private String nome;

    private String telefone;


    public PacienteDTO(PacienteEntity pacienteEntity) {
        BeanUtils.copyProperties(pacienteEntity, this);
    }
}
