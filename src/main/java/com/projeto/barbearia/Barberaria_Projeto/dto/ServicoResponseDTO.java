package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco
) {}
