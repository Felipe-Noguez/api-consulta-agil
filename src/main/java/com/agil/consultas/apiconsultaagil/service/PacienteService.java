package com.agil.consultas.apiconsultaagil.service;

import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteDTO;
import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import com.agil.consultas.apiconsultaagil.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteDTO cadastrarPaciente(PacienteCreateDTO pacienteCreateDTO) throws RegraDeNegocioException {
        Optional<PacienteEntity> pacienteDB = pacienteRepository.findByTelefone(pacienteCreateDTO.getTelefone());

        if (pacienteDB.isPresent()) {
            throw new RegraDeNegocioException("Paciente já cadastrado!");
        }

        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntity.setNome(pacienteCreateDTO.getNome());
        pacienteEntity.setTelefone(pacienteCreateDTO.getTelefone());

        pacienteRepository.save(pacienteEntity);

        return new PacienteDTO(pacienteEntity);
    }

    public PageDTO<PacienteDTO> listarPacientes(Integer page, Integer size) throws RegraDeNegocioException {
        if (page < 0 || size < 0) {
            throw new RegraDeNegocioException("Tamanho da página ou de elementos não podem ser menor que 0.");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PacienteEntity> pacienteEntities = pacienteRepository.findAll(pageRequest);

        List<PacienteDTO> pacienteDTO = pacienteEntities.getContent()
                .stream()
                .map(this::mapDTO)
                .collect(Collectors.toList());

        if (pacienteDTO.isEmpty()) {
            throw new RegraDeNegocioException("Dados não encontrados.");
        }

        return new PageDTO<>(pacienteEntities.getTotalElements(),
                pacienteEntities.getTotalPages(),
                page,
                size,
                pacienteDTO);
    }

    private PacienteDTO mapDTO(PacienteEntity pacienteEntity) {
        return new PacienteDTO(pacienteEntity);
    }

    public PacienteEntity buascarPacientePorId(Long idPaciente) throws RegraDeNegocioException {
        return pacienteRepository.findById(idPaciente).orElseThrow(() ->
                 new RegraDeNegocioException("Paciente não encontrado")
        );
    }
}
