package com.projeto.barbearia.Barberaria_Projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {
    // Nenhum campo adicional no momento
    // Futuramente poderá ter atributos específicos como fidelidade ou histórico

}