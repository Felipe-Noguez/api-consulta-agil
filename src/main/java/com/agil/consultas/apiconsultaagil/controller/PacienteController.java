package com.agil.consultas.apiconsultaagil.controller;

import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteDTO;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import com.agil.consultas.apiconsultaagil.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteDTO> cadastrarPaciente(PacienteCreateDTO pacienteCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pacienteService.cadastrarPaciente(pacienteCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar-pacientes")
    public ResponseEntity<PageDTO<PacienteDTO>> listarPacientes(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) throws RegraDeNegocioException {
        return new ResponseEntity<>(pacienteService.listarPacientes(page, size), HttpStatus.OK);
    }
}
