package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico,Long> {

    // Buscar serviços por parte do nome (case-insensitive)
    List<Servico> findByNomeContainingIgnoreCase(String nome);

    // Buscar serviços dentro de uma faixa de preço
    List<Servico> findByPrecoBetween(BigDecimal min,BigDecimal max);

    // Estatísticas: média de preço
    @Query("SELECT AVG(s.preco) FROM Servico s")
    BigDecimal mediaPreco();

    // Estatísticas: contagem de serviços
    @Query("SELECT COUNT(s) FROM Servico s")
    Long TotalServicos();

    // Estatísticas: preço máximo
    @Query("SELECT MAX(s.preco) FROM Servico s")
    BigDecimal precoMAXIMO();

    // Estatísticas: preço mínimo
    @Query("SELECT MIN(s.preco) FROM Servico s")
    BigDecimal precoMINIMO();

    /*
        Queries customizadas com @Query para estatísticas:

    AVG, COUNT, MAX, MIN para obter métricas rápidas sobre os serviços.
     */

}
