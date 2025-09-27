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
@Table(name = "presencas")
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Agendamento agendamento;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private boolean ativo; // true = cliente presente, false = já saiu
}
