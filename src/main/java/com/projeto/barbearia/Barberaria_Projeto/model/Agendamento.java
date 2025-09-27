package com.projeto.barbearia.Barberaria_Projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    private Cliente cliente;
    private Funcionario barbeiro;
    private Servico servico;
    private LocalDateTime dataHora;
    private String status;   // Exemplo: "PENDENTE", "CONFIRMADO", "CANCELADO", "FINALIZADO"
    private boolean checkIn; // true se o cliente já chegou
}
