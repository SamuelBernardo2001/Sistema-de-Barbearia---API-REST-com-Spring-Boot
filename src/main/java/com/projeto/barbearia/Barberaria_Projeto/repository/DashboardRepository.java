package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Agendamento, Long> {

    // Total de agendamentos
    @Query("SELECT COUNT(a) FROM Agendamento a")
    Long countTotalAgendamentos();

    // Agendamentos de hoje
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE DATE(a.dataHora) = CURRENT_DATE")
    Long countAgendamentosHoje();

    // Agendamentos da semana
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE WEEK(a.dataHora) = WEEK(CURRENT_DATE)")
    Long countAgendamentosSemana();

    // Receita total
    @Query("SELECT SUM(s.preco) FROM Agendamento a JOIN a.servico s WHERE a.status = 'FINALIZADO'")
    Double calcularReceitaTotal();

    // Receita da semana
    @Query("SELECT SUM(s.preco) FROM Agendamento a JOIN a.servico s WHERE a.status = 'FINALIZADO' AND WEEK(a.dataHora) = WEEK(CURRENT_DATE)")
    Double calcularReceitaSemana();

    // Ticket médio
    @Query("SELECT AVG(s.preco) FROM Agendamento a JOIN a.servico s WHERE a.status = 'FINALIZADO'")
    Double calcularTicketMedio();

    // Clientes presentes hoje
    @Query("SELECT COUNT(p) FROM Presenca p WHERE DATE(p.entrada) = CURRENT_DATE AND p.ativo = true")
    Long countClientesPresentesHoje();

    // Média geral das avaliações
    @Query("SELECT AVG(av.nota) FROM Avaliacao av")
    Double calcularMediaAvaliacoes();
}
