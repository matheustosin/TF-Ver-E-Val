package com.group;

import java.util.List;
import java.util.stream.Collectors;

public class RegraImpostoComprasGrandes implements RegraImposto {
    @Override
    public double calcular(List<ItemVenda> itens) {
        if (itens.isEmpty()) {
            return 0.0;
        }
        // Ordena a lista em ordem crescente do custo dos itens
        itens = itens.stream()
            .sorted((it1,it2) -> (int)((it2.getValorVendido()*it2.getQuantidade()) - (it1.getValorVendido()*it1.getQuantidade())))
            .collect(Collectors.toList());
        // Calcula 10% para os 3 itens mais caros (se houverem)
        int qtdade = Math.min(3,itens.size());
        double imposto = 0.0;
        for(int i=0;i<qtdade;i++){
            imposto += (itens.get(i).getValorVendido()*0.1);
        }
        // Calcula o imposto sobre os demais itens se houverem
        for(int i=qtdade;i<itens.size();i++){
            imposto += (itens.get(i).getValorVendido()*0.05);
        }
        return imposto;
    }
}
