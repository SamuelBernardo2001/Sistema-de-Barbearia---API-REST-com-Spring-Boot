package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        String clienteNome,
        String barbeiroNome,
        String servicoNome,
        BigDecimal servicoPreco,
        LocalDateTime dataHora
) {
}
