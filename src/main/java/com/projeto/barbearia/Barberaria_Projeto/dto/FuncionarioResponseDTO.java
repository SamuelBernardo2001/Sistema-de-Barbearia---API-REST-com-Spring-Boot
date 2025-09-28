package com.projeto.barbearia.Barberaria_Projeto.dto;

import com.projeto.barbearia.Barberaria_Projeto.model.Cargo;

import java.time.LocalDateTime;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        Cargo cargo,
        LocalDateTime dataCadastro
) {
}
