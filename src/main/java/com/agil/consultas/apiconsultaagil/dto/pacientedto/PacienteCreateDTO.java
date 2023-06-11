package com.agil.consultas.apiconsultaagil.dto.pacientedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteCreateDTO {

    private String nome;

    private String telefone;
}
