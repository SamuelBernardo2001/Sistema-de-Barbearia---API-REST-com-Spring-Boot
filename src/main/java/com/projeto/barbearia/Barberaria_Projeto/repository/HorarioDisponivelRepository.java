package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.HorarioDisponivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {

    // Buscar todos os horários de um barbeiro
    List<HorarioDisponivel> findAllById(Long barbeiroID);

    // Verificar se existe sobreposição de horário para um barbeiro
    @Query("""
           SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
           FROM HorarioDisponivel h
           WHERE h.barbeiro.id = :barbeiroId
           AND (
                (h.inicio <= :fim AND h.fim >= :inicio) -- sobreposição
           )
           """)
    boolean existeConflito(
            @Param("barbeiroId") Long barbeiroId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    // Listar horários futuros de um barbeiro
    @Query("SELECT h FROM HorarioDisponivel h WHERE h.barbeiro.id = :barbeiroId AND h.inicio >= :agora ORDER BY h.inicio ASC")
    List<HorarioDisponivel> horariosFuturos(
            @Param("barbeiroId") Long barbeiroId,
            @Param("agora") LocalDateTime agora
    );

    // Encontrar lacunas disponíveis em um intervalo
    @Query("""
           SELECT h FROM HorarioDisponivel h
           WHERE h.barbeiro.id = :barbeiroId
           AND h.inicio >= :inicioPeriodo
           AND h.fim <= :fimPeriodo
           ORDER BY h.inicio ASC
           """)
    List<HorarioDisponivel> buscarDisponiveisNoPeriodo(
            @Param("barbeiroId") Long barbeiroId,
            @Param("inicioPeriodo") LocalDateTime inicioPeriodo,
            @Param("fimPeriodo") LocalDateTime fimPeriodo
    );
}
