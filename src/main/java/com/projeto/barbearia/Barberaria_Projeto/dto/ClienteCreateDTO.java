package com.projeto.barbearia.Barberaria_Projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteCreateDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "A senha é obrigatório")
        @Size(min = 6,message = "A senha deve ter no mínimo 6 caracteres")
        String senha
) {
}
