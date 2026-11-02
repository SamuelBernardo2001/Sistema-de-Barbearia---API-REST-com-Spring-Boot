package com.projeto.barbearia.Barberaria_Projeto.dto;

import com.projeto.barbearia.Barberaria_Projeto.validation.HorarioValido;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@HorarioValido
public record HorarioDisponivelDTO(

        @NotNull(message = "O ID do barbeiro é obrigatório")
        Long barbeiroId,

        @NotNull(message = "Campo 'inicio' é obrigatório")
        LocalTime inicio,

        @NotNull(message = "Campo 'fim' é obrigatório")
        LocalTime fim

) {}
