package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ServicoDeVendasIntegrationTest {

  @ParameterizedTest
  @MethodSource("imposto")
  void testaCalculoImposto(RegraImposto regraImpostoTeste, Optional<List<ItemVenda>> maisItens, double resultadoEsperado) {
    RegraImposto regraImposto = regraImpostoTeste;
    FactoryValidacao factoryValidacao = mock(FactoryValidacao.class);
    Produtos produtos = mock(Produtos.class);
    Estoque estoque = mock(Estoque.class);

    List<ItemVenda> itens = new ArrayList<>(3);
    itens.add(new ItemVenda(1, 10, 2, 1000));
    itens.add(new ItemVenda(2, 30, 3, 2000));
    itens.add(new ItemVenda(3, 50, 1, 1500));

    maisItens.ifPresent(itens::addAll);

    ServicoDeVendas servicoDeVendas = new ServicoDeVendas(produtos, estoque, regraImposto, factoryValidacao);
    assertEquals(resultadoEsperado, servicoDeVendas.calculaImpostos(itens));
  }

  private static Stream<Arguments> imposto() {
    return Stream.of(
      Arguments.of(new RegraImpostoOriginal(), Optional.empty(), 450),
      Arguments.of(new RegraImpostoComprasGrandes(), Optional.empty(), 450),
      Arguments.of(
        new RegraImpostoComprasGrandes(),
        Optional.of(List.of(new ItemVenda(5, 70, 5, 1500))),
        525)
    );
  }
}
