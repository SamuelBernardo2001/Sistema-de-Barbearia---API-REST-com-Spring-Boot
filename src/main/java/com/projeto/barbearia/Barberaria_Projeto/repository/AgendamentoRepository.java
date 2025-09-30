package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Agendamento;
import com.projeto.barbearia.Barberaria_Projeto.model.Cliente;
import com.projeto.barbearia.Barberaria_Projeto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // busca cliente por id
    List<Agendamento> findByCliente (Cliente cliente);

    // busca barbeiro por id
    List<Agendamento> findByBarbeiro (Funcionario barbeiro);

    // Buscar agendamentos futuros de um barbeiro
    @Query("SELECT a FROM Agendamento a WHERE a.barbeiro = :barbeiro AND a.dataHora > :agora")
    List<Agendamento> findAgendamentosFuturosByBarbeiro(Funcionario barbeiro, LocalDateTime agora);

    // Verificar se um barbeiro já tem agendamento em um horário específico
    @Query("SELECT COUNT(a) > 0 FROM Agendamento a WHERE a.barbeiro = :barbeiro AND a.dataHora = :dataHora AND a.status <> 'CANCELADO'")
    boolean existsByBarbeiroAndHorario(Funcionario barbeiro, LocalDateTime dataHora);

    // Contar agendamentos confirmados de um cliente
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.cliente = :cliente AND a.status = 'CONFIRMADO'")
    Long countAgendamentosConfirmadosCliente(Cliente cliente);

    // Buscar agendamentos por status
    List<Agendamento> findByStatus(String status);
}
