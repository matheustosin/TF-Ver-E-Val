package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;

public class ServicoDeVendasTest {
    private ServicoDeVendas servicoDeVendas;

    @Test
    public void calculaSubtotal() {
        
        RegraImposto regraImposto = mock(RegraImposto.class);
        FactoryValidacao factoryValidacao = mock(FactoryValidacao.class);

        Produtos produtos = mock(Produtos.class);
        when(produtos.recupera(10)).thenReturn(new Produto(10, "Prod10", 1000.0));
        when(produtos.recupera(30)).thenReturn(new Produto(30, "Prod30", 2000.0));
        when(produtos.recupera(50)).thenReturn(new Produto(50, "Prod15", 1500.0));

        Estoque estoque = mock(Estoque.class);
        when(estoque.recupera(10)).thenReturn(new ItemEstoque(10, 5));
        when(estoque.recupera(30)).thenReturn(new ItemEstoque(30, 3));
        when(estoque.recupera(50)).thenReturn(new ItemEstoque(50, 15));

        List <ItemVenda> itens = new ArrayList < > (3);
        itens.add(new ItemVenda(1, 10, 2, 1000));
        itens.add(new ItemVenda(2, 30, 3, 2000));
        itens.add(new ItemVenda(3, 50, 1, 1500));

        this.servicoDeVendas = new ServicoDeVendas(produtos, estoque, regraImposto, factoryValidacao);
        assertEquals(4500, servicoDeVendas.calculaSubtotal(itens));
    }
}
