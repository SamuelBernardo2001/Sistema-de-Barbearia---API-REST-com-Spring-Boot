package com.projeto.barbearia.Barberaria_Projeto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.projeto.barbearia.Barberaria_Projeto.dto.HorarioDisponivelDTO;
import java.time.LocalTime;

public class HorarioValidoValidator implements ConstraintValidator<HorarioValido, HorarioDisponivelDTO> {

    @Override
    public boolean isValid(HorarioDisponivelDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true; // outro @NotNull deve validar nullidade do objeto se necessário

        LocalTime inicio = dto.inicio();
        LocalTime fim = dto.fim();

        // Se inicio ou fim forem null, deixam outras anotações (@NotNull) reportarem o erro.
        if (inicio == null || fim == null) return true;

        return inicio.isBefore(fim);
    }
}
