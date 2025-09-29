package com.projeto.barbearia.Barberaria_Projeto.dto;

import java.util.List;

public record PresencaEstatisticasDTO(
        Long totalPresentesAtuais,
        Long totalHistorico,
        List<PresencaLotacaoBarbeiroDTO> lotacaoPorBarbeiro
) {
}
