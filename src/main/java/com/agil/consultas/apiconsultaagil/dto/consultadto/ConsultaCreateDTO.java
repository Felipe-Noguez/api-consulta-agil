package com.agil.consultas.apiconsultaagil.dto.consultadto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaCreateDTO {

    @NotBlank(message = "campo obrigatório, preencha e tente novamente")
    @NotNull(message = "campo obrigatório, preencha e tente novamente")
    @Schema(description = "Especialidade do médico à ser consultado", example = "Clínico Geral")
    private String especialidade;

    @FutureOrPresent(message = "Verique a data, ela não pode ser anterior a data atual.")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @Schema(description = "Data e horário da consulta", example = "25-10-2023 11:00:00")
    private LocalDateTime dataHoraConsulta;

    @NotNull(message = "campo obrigatório, preencha e tente novamente")
    @Schema(description = "Id do paciente", example = "1")
    private Long idPaciente;
}
