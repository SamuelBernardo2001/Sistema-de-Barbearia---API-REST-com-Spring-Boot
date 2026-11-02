package com.projeto.barbearia.Barberaria_Projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    private String nome;
    private BigDecimal preco;
}
