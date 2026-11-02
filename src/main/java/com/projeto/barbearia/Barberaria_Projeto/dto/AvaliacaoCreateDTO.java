package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliacaoCreateDTO(

        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteID,

        @NotNull(message = "O ID do barbeiro é obrigatório")
        Long barbeiroID,

        @NotNull(message = "A nota é obrigatório")
        @Min(value = 1, message = "A nota mínima é 1")
        @Max(value = 5, message = "A nota máxima é 5")
        Integer nota,

        @Size(max = 500, message = "O comentário não pode ultrapassar 500 caracteres")
        String comentario
) {
}
