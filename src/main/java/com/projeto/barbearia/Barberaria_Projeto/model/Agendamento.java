package com.projeto.barbearia.Barberaria_Projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Funcionario barbeiro;
    @ManyToOne
    private Servico servico;
    private LocalDateTime dataHora;
    private String status;   // Exemplo: "PENDENTE", "CONFIRMADO", "CANCELADO", "FINALIZADO"
    private boolean checkIn; // true se o cliente já chegou
}
