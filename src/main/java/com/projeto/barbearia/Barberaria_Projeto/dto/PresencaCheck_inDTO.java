package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.NotNull;

public record PresencaCheck_inDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "O ID do agendamento é obrigatório")
        Long agendamentoId
) {
}
