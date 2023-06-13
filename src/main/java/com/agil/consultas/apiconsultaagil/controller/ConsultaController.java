package com.agil.consultas.apiconsultaagil.controller;

import com.agil.consultas.apiconsultaagil.controller.doc.ConsultaDocController;
import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaPacienteDTO;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import com.agil.consultas.apiconsultaagil.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/consulta")
public class ConsultaController implements ConsultaDocController {

    private final ConsultaService consultaService;

    @PostMapping("/cadastrar-consulta")
    public ResponseEntity<ConsultaDTO> cadastrarConsulta(@Valid @RequestBody ConsultaCreateDTO consultaCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(consultaService.cadastrarConsulta(consultaCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar-consultas")
    public ResponseEntity<PageDTO<ConsultaPacienteDTO>> listarConsultas(@RequestParam(defaultValue = "0") Integer page,
                                                                        @RequestParam(defaultValue = "10") Integer size) throws RegraDeNegocioException {
        return new ResponseEntity<>(consultaService.listarConsultas(page, size), HttpStatus.OK);
    }

    @PutMapping("/atualizar-consulta")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@Valid @RequestBody ConsultaCreateDTO consultaCreateDTO,
                                                         @RequestParam Long idConsulta) throws RegraDeNegocioException {
        return new ResponseEntity<>(consultaService.atualizarConsulta(idConsulta, consultaCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelar-consulta")
    public ResponseEntity<ConsultaDTO> cancelarConsulta(Long idConsulta) throws RegraDeNegocioException {
        return new ResponseEntity<>(consultaService.cancelarConsulta(idConsulta), HttpStatus.OK);
    }
}
