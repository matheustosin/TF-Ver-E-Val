package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RegraImpostoComprasGrandesTest {

    @ParameterizedTest
    @MethodSource("entradas")
    void calculaDezPorcento(List<ItemVenda> itens, double resultadoEsperado) {
        RegraImposto regraImpostoComprasGrandes = new RegraImpostoComprasGrandes();
        double impostoCalculado = regraImpostoComprasGrandes.calcular(itens);
        assertEquals(resultadoEsperado, impostoCalculado);
    }

    private static Stream<Arguments> entradas() {
        return Stream.of(
            Arguments.of(List.of(), 0),
            Arguments.of(List.of(new ItemVenda(1, 10, 1, 1000)), 100),
            Arguments.of(
                List.of(
                    new ItemVenda(1, 10, 1, 1000),
                    new ItemVenda(2, 20, 3, 2000),
                    new ItemVenda(3, 30, 5, 3000)
                ), (300 + 200 + 100)
            ),
            Arguments.of(
                List.of(
                    new ItemVenda(1, 10, 1, 1000),
                    new ItemVenda(2, 20, 3, 2000),
                    new ItemVenda(3, 30, 5, 3000),
                    new ItemVenda(4, 40, 7, 4000),
                    new ItemVenda(5, 50, 9, 5000)
                ), (500 + 400 + 300 + 100 + 50)
            )
        );
    }
}
