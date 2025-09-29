package com.projeto.barbearia.Barberaria_Projeto.service;

import com.projeto.barbearia.Barberaria_Projeto.dto.PresencaCheck_inDTO;
import com.projeto.barbearia.Barberaria_Projeto.dto.PresencaResponseDTO;
import com.projeto.barbearia.Barberaria_Projeto.model.Agendamento;
import com.projeto.barbearia.Barberaria_Projeto.model.Cliente;
import com.projeto.barbearia.Barberaria_Projeto.model.Presenca;
import com.projeto.barbearia.Barberaria_Projeto.repository.PresencaRepository;
import com.projeto.barbearia.Barberaria_Projeto.servicesSubs.AgendamentoServiceSub;
import com.projeto.barbearia.Barberaria_Projeto.servicesSubs.ClienteServiceSub;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PresencaService {

    private static final int MAX_CAPACITY = 10;

    private final PresencaRepository presencaRepository;
    private final ClienteServiceSub clienteService;
    private final AgendamentoServiceSub agendamentoService;

    public PresencaService(PresencaRepository presencaRepository,
                           ClienteServiceSub clienteService,
                           AgendamentoServiceSub agendamentoService) {
        this.presencaRepository = presencaRepository;
        this.clienteService = clienteService;
        this.agendamentoService = agendamentoService;
    }

    // Converte DTO -> Entity (para criação)
    private Presenca toEntityCreate(PresencaCheck_inDTO dto, Cliente cliente, Agendamento agendamento) {
        Presenca presenca = new Presenca();
        presenca.setCliente(cliente);
        presenca.setAgendamento(agendamento);
        presenca.setEntrada(LocalDateTime.now());
        presenca.setAtivo(true);
        return presenca;
    }

    // Converte Entity -> ResponseDTO
    private PresencaResponseDTO toResponseDTO(Presenca p) {
        Long clienteId = p.getCliente() != null ? p.getCliente().getId() : null;
        String clienteNome = p.getCliente() != null ? p.getCliente().getNome() : null;
        Long agendamentoId = p.getAgendamento() != null ? p.getAgendamento().getId() : null;
        return new PresencaResponseDTO(
                p.getId(),
                clienteId,
                clienteNome,
                agendamentoId,
                p.getEntrada(),
                p.getSaida(),
                p.isAtivo()
        );
    }

    // check-in
    @Transactional
    public PresencaResponseDTO checkIn(PresencaCheck_inDTO dto) {
        // Validando cliente
        Cliente cliente = clienteService.buscarPorId(dto.clienteId());

        // Validando agendamento ( corrigido para dto.agendamentoId())
        Agendamento agendamento = agendamentoService.buscarPorId(dto.agendamentoId());
        if (agendamento == null || !"AGENDADO".equalsIgnoreCase(agendamento.getStatus())) {
            throw new DataIntegrityViolationException("Agendamento inválido ou não está no status AGENDADO");
        }

        // Check-in duplicado (cliente já presente)
        Optional<Presenca> presensaAtivaOpt = presencaRepository.findByClienteIdAndAtivoTrue(cliente.getId());
        if (presensaAtivaOpt.isPresent()) {
            throw new IllegalArgumentException("Cliente já está presente (check-in duplicado)");
        }

        // Testar lotação
        long presentes = presencaRepository.countByAtivoTrue();
        if (presentes >= MAX_CAPACITY) {
            throw new IllegalArgumentException("Lotação máxima atingida: " + MAX_CAPACITY);
        }

        // Criar presença
        Presenca presenca = toEntityCreate(dto, cliente, agendamento);
        presenca = presencaRepository.save(presenca);

        return toResponseDTO(presenca);
    }
}
