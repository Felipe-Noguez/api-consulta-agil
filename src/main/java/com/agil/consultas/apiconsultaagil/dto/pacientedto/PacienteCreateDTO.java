package com.agil.consultas.apiconsultaagil.dto.pacientedto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteCreateDTO {

    @NotBlank(message = "campo obrigatório, preencha e tente novamente")
    @NotNull(message = "campo obrigatório, preencha e tente novamente")
    @Schema(description = "Nome do paciente para o cadastro", example = "Mike")
    private String nome;

    @NotBlank(message = "campo obrigatório, preencha e tente novamente")
    @NotNull(message = "campo obrigatório, preencha e tente novamente")
    @Schema(description = "Telefone do paciente para o cadastro", example = "(51) 99999-1234")
    private String telefone;
}
