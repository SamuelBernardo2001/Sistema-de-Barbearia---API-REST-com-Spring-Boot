package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.math.BigDecimal;

/*
Total de agendamentos → Quantos já foram feitos desde o início.

Agendamentos de hoje → Quantos foram marcados/executados na data atual.

Agendamentos da semana → Total da semana corrente.

Receita → Valor total faturado no período.

Ticket médio → Valor médio por agendamento (receita ÷ quantidade de agendamentos).

Clientes presentes → Quantos compareceram de fato (via check-in de presença).

Média de avaliações → Nota média recebida pelos barbeiros.
 */

public record DashboardResponseDTO(
        Long totalAgendamentos,
        Long agendamentosHoje,
        Long agendamentosSemana,
        BigDecimal receitaTotal,
        BigDecimal receitaHoje,
        BigDecimal ticketMedio,
        Long clientesPresentes,
        Double mediaAvaliacoes
) {
}
