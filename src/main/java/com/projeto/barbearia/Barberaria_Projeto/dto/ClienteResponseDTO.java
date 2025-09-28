package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.time.LocalDateTime;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDateTime dataCadastro
) {
}
