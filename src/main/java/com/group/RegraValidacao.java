package com.group;

import java.util.List;

public interface RegraValidacao {
    public default void valida(Produtos produtos, Estoque estoque, List<ItemVenda> itens) {
        for (ItemVenda iv : itens) {
            final Produto produto = produtos.recupera(iv.getCodigoProduto());
            final int quantidade = iv.getQuantidade();
            ItemEstoque itemEstoque = estoque.recupera(produto.getCodigo());
            if (itemEstoque == null)
                throw new SistVendasException(SistVendasException.Causa.PRODUTO_NAO_CADASTRADO_ESTOQUE);
            if (!itemEstoque.disponivel(quantidade))
                throw new SistVendasException(SistVendasException.Causa.QUANTIDADE_INSUFICIENTE);
            verificacaoForaHoraComercial(produtos, itens);
        }
    }

    abstract void verificacaoForaHoraComercial(Produtos produtos, List<ItemVenda> itens);
}
