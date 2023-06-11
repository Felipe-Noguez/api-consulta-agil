package com.agil.consultas.apiconsultaagil.dto.consultadto;

import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaPacienteDTO {

    private PacienteDTO pacienteDTO;

    private ConsultaDTO consultaDTO;
}
