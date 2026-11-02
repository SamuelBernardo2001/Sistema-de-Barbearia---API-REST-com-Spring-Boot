package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
        Long id,
        String clienteNome,
        String barbeiroNome,
        LocalDateTime dataAvaliacao,
        Double mediaNotasBarbeirogit
) {
}
