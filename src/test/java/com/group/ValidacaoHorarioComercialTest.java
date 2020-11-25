package com.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ValidacaoHorarioComercialTest {

    @Test
    public void validaTresProdutosExistentes() {
        Produtos produtos = mock(Produtos.class);
        when(produtos.recupera(10)).thenReturn(new Produto(10, "Prod10", 1000.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30, "Prod30", 2000.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50, "Prod15", 1500.0));

        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10, 5));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30, 3));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50, 15));

        List <ItemVenda> itens = new ArrayList<>(3);
        itens.add(new ItemVenda(1, 10, 2, 1000));
        itens.add(new ItemVenda(2, 30, 3, 2000));
        itens.add(new ItemVenda(3, 50, 1, 1500));

        RegraValidacao regra = new ValidacaoHorarioComercial();
        assertDoesNotThrow(() -> regra.valida(produtos, estoque, itens));
    }

    @ParameterizedTest
    @MethodSource("validaRetornoExcecao")
    public void validaExcecoes(Produto produto, ItemEstoque itemEstoque, ItemVenda itemVenda) {
        Produtos produtosMock = mock(Produtos.class);
        when(produtosMock.recupera(produto.getCodigo())).thenReturn(produto);

        Estoque estoqueMock = mock(Estoque.class);
        when(estoqueMock.recupera(produto.getCodigo())).thenReturn(itemEstoque);

        List <ItemVenda> itens = new ArrayList < > (1);
        itens.add(itemVenda);

        RegraValidacao regra = new ValidacaoHorarioComercial();
        assertThrows(SistVendasException.class, () -> regra.valida(produtosMock, estoqueMock, itens));
    }

    private static Stream <Arguments> validaRetornoExcecao() {
        return Stream.of(
            Arguments.of(new Produto(10, "Prod10", 1000.0), new ItemEstoque(10, 1), new ItemVenda(1, 10, 2, 1000.0)),
            Arguments.of(new Produto(10, "Prod10", 1000.0), null, new ItemVenda(1, 10, 2, 1000.0))
        );
    }
}