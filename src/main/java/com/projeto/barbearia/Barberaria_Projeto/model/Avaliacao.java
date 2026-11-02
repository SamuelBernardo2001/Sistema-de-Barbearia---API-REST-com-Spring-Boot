package com.projeto.barbearia.Barberaria_Projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota; // ex = 1 a 5
    private String comentaria;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Funcionario barbeiro;
}
