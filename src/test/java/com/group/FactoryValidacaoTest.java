package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FactoryValidacaoTest {

    @ParameterizedTest
    @MethodSource("entradas")
    public void retornaValidacao(LocalTime hora, RegraValidacao regraValidacaoEsperada) {
        FactoryValidacao factory = new FactoryValidacao(hora);
        RegraValidacao regraValidacao = factory.getRegraValidacao();
        assertEquals(regraValidacaoEsperada.getClass(), regraValidacao.getClass());
    }

    private static Stream <Arguments> entradas() {
        return Stream.of(
            Arguments.of(LocalTime.of(7, 59, 59), new ValidacaoForaHorarioComercial()),
            Arguments.of(LocalTime.of(8, 0, 0), new ValidacaoHorarioComercial()),
            Arguments.of(LocalTime.of(12, 0, 0), new ValidacaoHorarioComercial()),
            Arguments.of(LocalTime.of(18, 0, 0), new ValidacaoHorarioComercial()),
            Arguments.of(LocalTime.of(18, 0, 1), new ValidacaoForaHorarioComercial())
        );
    }
}