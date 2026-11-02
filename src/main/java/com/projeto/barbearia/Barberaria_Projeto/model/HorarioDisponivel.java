package com.projeto.barbearia.Barberaria_Projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDisponivel {

    private Funcionario barbeiro;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}
