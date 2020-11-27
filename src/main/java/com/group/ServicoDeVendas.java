package com.group;

import java.util.List;

public class ServicoDeVendas {
    private RegraImposto regraImposto;
    private Produtos produtos;
    private Estoque estoque;
    private FactoryValidacao factoryValidacao;

    public ServicoDeVendas(Produtos produtos, Estoque estoque, RegraImposto regraImposto, FactoryValidacao factoryValidacao) {
        this.produtos = produtos;
        this.estoque = estoque;
        this.regraImposto = regraImposto;
        this.factoryValidacao = factoryValidacao;
    }

    public void valida(List<ItemVenda> itens) {
        factoryValidacao.getRegraValidacao().valida(produtos, estoque, itens);
    }

    public Double calculaSubtotal(List<ItemVenda> itens) {
        return (itens.stream().mapToDouble(ItemVenda::getValorVendido).sum());
    }

    public Double calculaImpostos(List<ItemVenda> itens) {
        return regraImposto.calcular(itens);
    }

    public Double calculaPrecoFinal(List<ItemVenda> itens) {
        return calculaSubtotal(itens) + calculaImpostos(itens);
    }

    public Double[] todosValores(List<ItemVenda> itens) {
        Double[] valores = new Double[2];
        valores[0] = calculaSubtotal(itens);
        valores[1] = calculaImpostos(itens);
        return valores;
    }
}
