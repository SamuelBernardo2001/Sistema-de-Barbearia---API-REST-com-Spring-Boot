package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.time.LocalDateTime;

public record PresencaResponseDTO(
        Long id,
        Long clienteId,
        String clienteNome,
        Long agendamentoId,
        LocalDateTime entrada,
        LocalDateTime saida,
        Boolean ativo
) {
}
