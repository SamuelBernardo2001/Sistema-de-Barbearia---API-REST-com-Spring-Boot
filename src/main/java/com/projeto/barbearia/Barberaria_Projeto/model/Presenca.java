package com.projeto.barbearia.Barberaria_Projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presenca {

    private Cliente cliente;
    private Agendamento agendamento;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean ativo; // true = cliente presente, false = já saiu
}
