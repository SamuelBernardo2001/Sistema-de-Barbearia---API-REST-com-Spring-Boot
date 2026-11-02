package com.projeto.barbearia.Barberaria_Projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    private int nota; // ex = 1 a 5
    private String comentaria;
    private Cliente cliente;
    private Funcionario barbeiro;
}
