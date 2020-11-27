package com.group;

import java.util.List;

public class ValidacaoForaHorarioComercial implements RegraValidacao {
    @Override
    public void verificacaoForaHoraComercial(Produtos produtos, List<ItemVenda> itens) {
        for (ItemVenda iv : itens) {
            final Produto produto = produtos.recupera(iv.getCodigoProduto());
            if ((produto.getPreco() * iv.getQuantidade()) > 5000.0){
                throw new SistVendasException(SistVendasException.Causa.VENDA_COM_ITEM_MUITO_CARO);
            }
        }
    }
}
