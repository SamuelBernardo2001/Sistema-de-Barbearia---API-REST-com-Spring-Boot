package com.projeto.barbearia.Barberaria_Projeto.dto;

import com.projeto.barbearia.Barberaria_Projeto.model.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        @NotBlank(message = "O Nome é obrigatório")
        String nome,

        @NotBlank(message = "O Email é obrigatório")
        @Email
        String email,

        @NotBlank(message = "O Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotNull(message = "O cargo é obrigatório")
        Cargo cargo
) {

}
