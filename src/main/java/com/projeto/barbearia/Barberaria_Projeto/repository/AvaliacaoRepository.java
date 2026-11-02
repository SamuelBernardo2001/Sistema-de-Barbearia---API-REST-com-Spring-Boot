package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Avaliacao;
import com.projeto.barbearia.Barberaria_Projeto.model.Cliente;
import com.projeto.barbearia.Barberaria_Projeto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {

    // Buscar avaliações por barbeiro
    List<Avaliacao> findByBarbeiro(Funcionario barbeiro);

    // Buscar avaliações por cliente
    List<Avaliacao> findByCliente(Cliente cliente);

    // Média de notas por barbeiro
    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.barbeiro.id = :barbeiroId")
    Double calcularMediaNotasBarbeiro(Long barbeiroId);

    // Média de notas geral
    @Query("SELECT AVG(a.nota) FROM Avaliacao a")
    Double calcularMediaGeral();

    // Contagem de avaliações por barbeiro
    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.barbeiro.id = :barbeiroId")
    Long countAvaliacoesPorBarbeiro(Long barbeiroId);
}
