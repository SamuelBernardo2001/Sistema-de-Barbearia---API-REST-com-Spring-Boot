package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.math.BigDecimal;

public record ServicoEstatisticasDTO(
        Long totalServicos,
        BigDecimal precoMinimo,
        BigDecimal precoMaximo,
        BigDecimal precoMedio) {
}
