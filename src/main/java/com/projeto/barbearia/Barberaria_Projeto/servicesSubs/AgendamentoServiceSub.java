package com.projeto.barbearia.Barberaria_Projeto.servicesSubs;

import com.projeto.barbearia.Barberaria_Projeto.model.Agendamento;

public interface AgendamentoServiceSub {
    Agendamento buscarPorId(Long id);
    void marcarCheckIn(Long agendamentoId); // marca check-in no agendamento
    void marcarCheckOut(Long agendamentoId);}
