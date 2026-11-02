package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

/**
 * DTO para operações de presença (check-in / check-out).
 * - clienteId e agendamentoId são obrigatórios.
 * - entrada/saida são timestamps (podem ficar nulos dependendo da operação).
 * - ativo indica se o cliente está presente no momento.
 *
 * Observação: validação cross-field (ex: saida >= entrada) não está aqui;
 * pode ser feita por validação customizada ou na camada de serviço.
 */
public record PresencaDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteID,

        @NotNull(message = "O ID do agendamento é obrigatório")
        Long agendamentoID,

        @PastOrPresent(message = "Entrada não pode estar no futuro")
        LocalDateTime entrada,      // pode ser null ao criar apenas referência ao agendamento

        @PastOrPresent(message = "Saída não pode estar no futuro")
        LocalDateTime saida,        // null até o cliente dar check-out

        @NotNull(message = "O campo ativo é obrigatório")
        Boolean ativo

) {
}
