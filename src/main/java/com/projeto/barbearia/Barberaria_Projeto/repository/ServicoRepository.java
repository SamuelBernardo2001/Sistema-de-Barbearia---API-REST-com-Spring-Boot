package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    // Buscar serviços por parte do nome (case-insensitive)
    List<Servico> findByNomeContainingIgnoreCase(String nome);

    //buscar serviço pelo nome (para validar nome único)
    Optional<Servico> findByNome(String nome);

    // Buscar serviços dentro de uma faixa de preço
    List<Servico> findByPrecoBetween(BigDecimal min, BigDecimal max);

    // Estatísticas: média de preço
    @Query("SELECT AVG(s.preco) FROM Servico s")
    Optional<BigDecimal> calcularPrecoMedio();

    // Estatísticas: contagem de serviços
    @Query("SELECT COUNT(s) FROM Servico s")
    Long totalServicos();

    // Estatísticas: preço máximo
    @Query("SELECT MAX(s.preco) FROM Servico s")
    BigDecimal precoMaximo();

    // Estatísticas: preço mínimo
    @Query("SELECT MIN(s.preco) FROM Servico s")
    BigDecimal precoMinimo();

    // ⚠️ Verificar se serviço possui agendamentos ativos
    @Query("""
        SELECT COUNT(a) FROM Agendamento a 
        WHERE a.servico = :servico 
        AND a.status IN ('PENDENTE','CONFIRMADO')
    """)
    Long countAgendamentosAtivos(Servico servico);
}
