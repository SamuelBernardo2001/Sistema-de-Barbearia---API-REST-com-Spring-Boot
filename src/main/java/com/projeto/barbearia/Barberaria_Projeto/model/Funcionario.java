package com.projeto.barbearia.Barberaria_Projeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa{

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(name = "ativo")
    private boolean ativo;
}
