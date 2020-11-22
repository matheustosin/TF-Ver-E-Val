package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RegraImpostoOriginalTest {

    @ParameterizedTest
    @MethodSource("entradas")
    public void calculaDezPorcento(List<ItemVenda> itens, double resultadoEsperado) {
        RegraImposto regraImpostoOriginal = new RegraImpostoOriginal();
        double impostoCalculado = regraImpostoOriginal.calcular(itens);
        assertEquals(resultadoEsperado, impostoCalculado);
    }

    private static Stream<Arguments> entradas() {
        return Stream.of(
            Arguments.of(
                List.of(), 0,
                List.of(new ItemVenda(1, 10, 1, 1000)), 100,
                List.of(new ItemVenda(1, 10, 1, 1000), new ItemVenda(2, 20, 3, 2000), new ItemVenda(3, 30, 5, 3000)), 2200
            )
        );
    }
}