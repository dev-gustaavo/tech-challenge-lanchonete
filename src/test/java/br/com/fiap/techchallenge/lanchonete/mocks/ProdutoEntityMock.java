package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ProdutoEntity;

import java.math.BigDecimal;

public class ProdutoEntityMock {

    public static ProdutoEntity getProdutoEntity() {
        return new ProdutoEntity()
                .setNome("produto")
                .setCategoria("LANCHE")
                .setPreco(BigDecimal.valueOf(10))
                .setDescricao("descricao")
                .setImagemPath("path");
    }

    public static ProdutoEntity getProdutoEntityCategoriaNull() {
        return new ProdutoEntity()
                .setNome("produto")
                .setCategoria(null)
                .setPreco(BigDecimal.valueOf(10))
                .setDescricao("descricao")
                .setImagemPath("path");
    }
}
