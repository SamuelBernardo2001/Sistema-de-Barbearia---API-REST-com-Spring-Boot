package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoCreateDTO(
        @NotBlank(message = "O nome do serviço é obrigatório")
        String nome,

        @NotNull(message = "O preço do serviço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser positivo")
        BigDecimal preco
) {
}
