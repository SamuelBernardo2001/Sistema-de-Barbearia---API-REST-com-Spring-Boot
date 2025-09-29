package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    // Para Buscar presenças ativas
    List<Presenca> findByAtivoTrue();

    // Buscar presenças de um cliente específico
    List<Presenca> findByClienteId(Long clienteID);

    // Buscar presença ativa de um cliente
    Optional<Presenca> findByClienteIdAndAtivoTrue(Long clienteId);

    // Contar presenças em um período específico
    @Query("SELECT COUNT(p) FROM Presenca p WHERE p.entrada >= :inicio AND p.saida <= :fim")
    Long contarPresencasEntre(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    // Listar clientes presentes no momento (sem hora de saída)
    @Query("SELECT p FROM Presenca p WHERE p.entrada <= :agora AND (p.saida IS NULL OR p.saida >= :agora) AND p.ativo = true")
    List<Presenca> clientesPresentes(@Param("agora") LocalDateTime agora);

    // Lotação por barbeiro (JOIN via agendamento -> barbeiro)
    @Query("SELECT a.barbeiro.id, COUNT(p) FROM Presenca p JOIN p.agendamento a WHERE p.entrada <= :agora AND (p.saida IS NULL OR p.saida >= :agora) AND p.ativo = true GROUP BY a.barbeiro.id")
    List<Object[]> lotacaoPorBarbeiro(@Param("agora") LocalDateTime agora);

    // Listar histórico completo de presenças
    @Query("SELECT p FROM Presenca p ORDER BY p.entrada DESC")
    List<Presenca> historicoPresencas();
    // Contagem rápida de presentes ativos
    Long countByAtivoTrue();
    /*
            Queries customizadas:

    Contar presenças em período:
    Permite gerar relatórios de lotação ou frequência.

    Clientes presentes agora:
    Lista clientes dentro da barbearia no momento, útil para monitoramento em tempo real.

    Lotação por barbeiro:
    Conta clientes por barbeiro para medir ocupação.

    Histórico completo de presenças:
    Ordena por data de entrada para relatórios e auditoria.

     */
}
