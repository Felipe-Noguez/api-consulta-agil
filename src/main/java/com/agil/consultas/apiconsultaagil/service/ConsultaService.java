package com.agil.consultas.apiconsultaagil.service;

import com.agil.consultas.apiconsultaagil.dto.PageDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaCreateDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaDTO;
import com.agil.consultas.apiconsultaagil.dto.consultadto.ConsultaPacienteDTO;
import com.agil.consultas.apiconsultaagil.dto.pacientedto.PacienteDTO;
import com.agil.consultas.apiconsultaagil.entities.ConsultaEntity;
import com.agil.consultas.apiconsultaagil.entities.PacienteEntity;
import com.agil.consultas.apiconsultaagil.entities.pk.PacienteConsultaPK;
import com.agil.consultas.apiconsultaagil.exceptions.ConsultaAgendadaException;
import com.agil.consultas.apiconsultaagil.exceptions.PacienteNaoAssociadoException;
import com.agil.consultas.apiconsultaagil.exceptions.RegraDeNegocioException;
import com.agil.consultas.apiconsultaagil.repository.ConsultaRepository;
import com.agil.consultas.apiconsultaagil.repository.PacienteConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteConsultaRepository pacienteConsultaRepository;
    private final PacienteService pacienteService;

    public ConsultaDTO cadastrarConsulta(ConsultaCreateDTO consultaCreateDTO) throws RegraDeNegocioException {

        try {
            verificarDataHoraConsulta(consultaCreateDTO.getDataHoraConsulta());

            if (consultaCreateDTO.getDataHoraConsulta() == null) {
                throw new RegraDeNegocioException("Por favor, insira data e hora no formato '01-08-2023 10:15:00' e tente novamente!");
            }

            PacienteEntity pacienteDB = pacienteService.buascarPacientePorId(consultaCreateDTO.getIdPaciente());

            PacienteEntity pacienteEntity = new PacienteEntity();
            pacienteEntity.setIdPaciente(pacienteDB.getIdPaciente());
            pacienteEntity.setNome(pacienteDB.getNome());
            pacienteEntity.setTelefone(pacienteDB.getTelefone());

            ConsultaEntity consultaEntity = new ConsultaEntity();
            consultaEntity.setEspecialidade(consultaCreateDTO.getEspecialidade());
            consultaEntity.setDataHoraConsulta(consultaCreateDTO.getDataHoraConsulta());

            PacienteConsultaPK pacienteConsultaPK = new PacienteConsultaPK();
            pacienteConsultaPK.setPacienteEntity(pacienteEntity);
            pacienteConsultaPK.setConsultaEntity(consultaEntity);

            Set<PacienteConsultaPK> consultas = new HashSet<>();
            consultas.add(pacienteConsultaPK);
            consultaEntity.setPacienteConsultaPK(consultas);

            consultaRepository.save(consultaEntity);

            return new ConsultaDTO(consultaEntity);
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao cadastrar o paciente.", e);
        }

    }

    public PageDTO<ConsultaPacienteDTO> listarConsultas(Integer page, Integer size) throws RegraDeNegocioException {
        if (page < 0 || size < 0) {
            throw new RegraDeNegocioException("Tamanho da página ou de elementos não podem ser menor que 0.");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PacienteConsultaPK> consultas = pacienteConsultaRepository.findAll(pageRequest);

        List<ConsultaPacienteDTO> consultaDTO = consultas.getContent()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        if (consultaDTO.isEmpty()) {
            throw new RegraDeNegocioException("Dados não encontrados.");
        }

        return new PageDTO<>(consultas.getTotalElements(),
                consultas.getTotalPages(),
                page,
                size,
                consultaDTO);
    }

    public ConsultaDTO atualizarConsulta(Long idConsulta, ConsultaCreateDTO consultaCreateDTO) throws RegraDeNegocioException {
        try {
            ConsultaEntity consultaAtualizar = buscarConsultaPorId(idConsulta);

            if (consultaCreateDTO.getDataHoraConsulta() == null) {
                throw new RegraDeNegocioException("Por favor, insira data e hora no formato '01-08-2023 10:15:00' e tente novamente!");
            }

//            verificarDataHoraConsulta(consultaCreateDTO.getDataHoraConsulta());
            verificarPertencimentoPaciente(consultaAtualizar, consultaCreateDTO.getIdPaciente());

            PacienteEntity pacienteDB = pacienteService.buascarPacientePorId(consultaCreateDTO.getIdPaciente());

            consultaAtualizar.setEspecialidade(consultaCreateDTO.getEspecialidade());
            consultaAtualizar.setDataHoraConsulta(consultaCreateDTO.getDataHoraConsulta());

            PacienteConsultaPK pacienteConsultaPK = new PacienteConsultaPK();
            pacienteConsultaPK.setPacienteEntity(pacienteDB);
            pacienteConsultaPK.setConsultaEntity(consultaAtualizar);

            consultaAtualizar.getPacienteConsultaPK().add(pacienteConsultaPK);

            consultaRepository.save(consultaAtualizar);

            return new ConsultaDTO(consultaAtualizar);
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro ao cadastrar o paciente.", e);
        }

    }


    public ConsultaDTO cancelarConsulta(Long idConsulta) throws RegraDeNegocioException {
        ConsultaEntity consultaDB = this.buscarConsultaPorId(idConsulta);

        consultaRepository.delete(consultaDB);

        return new ConsultaDTO(consultaDB);
    }

    public ConsultaEntity buscarConsultaPorId(Long idConsulta) throws RegraDeNegocioException {
        return consultaRepository.findById(idConsulta).orElseThrow(() ->
                new RegraDeNegocioException("Consulta não encontrada")
        );
    }

    private ConsultaPacienteDTO converterParaDTO(PacienteConsultaPK pacienteConsultaPK) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setIdPaciente(pacienteConsultaPK.getPacienteEntity().getIdPaciente());
        pacienteDTO.setNome(pacienteConsultaPK.getPacienteEntity().getNome());
        pacienteDTO.setTelefone(pacienteConsultaPK.getPacienteEntity().getTelefone());

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setIdConsulta(pacienteConsultaPK.getConsultaEntity().getIdConsulta());
        consultaDTO.setEspecialidade(pacienteConsultaPK.getConsultaEntity().getEspecialidade());
        consultaDTO.setDataHoraConsulta(pacienteConsultaPK.getConsultaEntity().getDataHoraConsulta());

        ConsultaPacienteDTO consultaPacienteDTO = new ConsultaPacienteDTO();
        consultaPacienteDTO.setPacienteDTO(pacienteDTO);
        consultaPacienteDTO.setConsultaDTO(consultaDTO);

        return consultaPacienteDTO;
    }

    private void verificarDataHoraConsulta(LocalDateTime novaDataHora) throws ConsultaAgendadaException {
        Optional<ConsultaEntity> consultaExistente = consultaRepository.findByDataHoraConsulta(novaDataHora);
        if (consultaExistente.isPresent()) {
            throw new ConsultaAgendadaException();
        }
    }

    private void verificarPertencimentoPaciente(ConsultaEntity consulta, Long idPaciente) throws PacienteNaoAssociadoException {
        boolean pacienteAssociado = consulta.getPacienteConsultaPK().stream()
                .anyMatch(pk -> pk.getPacienteEntity().getIdPaciente().equals(idPaciente));

        if (!pacienteAssociado) {
            throw new PacienteNaoAssociadoException();
        }
    }

}
