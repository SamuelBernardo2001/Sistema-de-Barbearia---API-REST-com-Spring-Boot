package com.projeto.barbearia.Barberaria_Projeto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = HorarioValidoValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
public @interface HorarioValido {
    String message() default "Horário inválido: 'inicio' deve ser antes de 'fim'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
