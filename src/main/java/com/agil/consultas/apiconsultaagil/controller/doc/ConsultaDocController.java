package com.agil.consultas.apiconsultaagil.controller.doc;

import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaPacienteDTO;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ConsultaDocController {

    @Operation(summary = "Cadastra consulta para o paciente", description = "Realiza a inserção dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar-consulta")
    public ResponseEntity<ConsultaDTO> cadastrarConsulta(@Valid @RequestBody ConsultaCreateDTO consultaCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Pesquisa no banco e retorna uma lista paginada de consultas com o paciente relacionado", description = "Realiza a pesquisa dos registros no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-consultas")
    public ResponseEntity<PageDTO<ConsultaPacienteDTO>> listarConsultas(@RequestParam(defaultValue = "0") Integer page,
                                                                        @RequestParam(defaultValue = "10") Integer size) throws RegraDeNegocioException;

    @Operation(summary = "Atualiza a consulta para o paciente", description = "Realiza a atualização dos registros no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/atualizar-consulta")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(@Valid @RequestBody ConsultaCreateDTO consultaCreateDTO,
                                                         @RequestParam Long idConsulta) throws RegraDeNegocioException;

    @Operation(summary = "Remove a consulta agendada do paciente", description = "Realiza a remoção dos registros no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Remoção realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/cancelar-consulta")
    public ResponseEntity<ConsultaDTO> cancelarConsulta(Long idConsulta) throws RegraDeNegocioException;
}
