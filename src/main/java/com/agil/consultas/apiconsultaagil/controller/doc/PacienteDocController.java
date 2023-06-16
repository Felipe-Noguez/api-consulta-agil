package com.agil.consultas.apiconsultaagil.controller.doc;

import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteDTO;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface PacienteDocController {

    @Operation(summary = "Cadastro de paciente", description = "Realiza a inserção dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar-paciente")
    public ResponseEntity<PacienteDTO> cadastrarPaciente(@Valid @RequestBody PacienteCreateDTO pacienteCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Pesquisa no banco e retorna uma lista paginada de pacientes", description = "Realiza a pesquisa dos registros no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-pacientes")
    public ResponseEntity<PageDTO<PacienteDTO>> listarPacientes(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) throws RegraDeNegocioException;
}
