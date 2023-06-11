package com.agil.consultas.apiconsultaagil.dto.consultadto;

import com.agil.consultas.apiconsultaagil.entities.ConsultaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaDTO {

    private Long idConsulta;

    private String especialidade;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHoraConsulta;

    public ConsultaDTO(ConsultaEntity consultaEntity) {
        BeanUtils.copyProperties(consultaEntity, this);
    }
}
