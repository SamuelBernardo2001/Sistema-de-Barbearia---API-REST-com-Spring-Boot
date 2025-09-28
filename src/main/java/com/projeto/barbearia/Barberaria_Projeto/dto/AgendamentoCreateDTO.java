package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(

        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteID,

        @NotNull(message = "O ID do barbeiro é obrigatório")
        Long barbeiroID,

        @NotNull(message = "O ID do servico é obrigatorio")
        Long servicoID,

        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        @Future(message = "A data e hora do agendamento devem estar no futuro")
        LocalDateTime dataCadastro
) {
}
