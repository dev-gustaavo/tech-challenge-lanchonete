package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.CategoriaProduto;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;

import java.math.BigDecimal;

public class ProdutoMock {

    public static Produto getProduto() {
        return new Produto()
                .setId(1)
                .setNome("produto")
                .setDescricao("descricao")
                .setCategoria(CategoriaProduto.LANCHE)
                .setPreco(BigDecimal.valueOf(10))
                .setImagemPath("path");
    }

    public static Produto getProdutoCategoriaNull() {
        return new Produto()
                .setId(1)
                .setNome("produto")
                .setDescricao("descricao")
                .setCategoria(null)
                .setPreco(BigDecimal.valueOf(10))
                .setImagemPath("path");
    }

    public static Produto getProdutoSemId() {
        return new Produto()
                .setId(null)
                .setNome("produto")
                .setDescricao("descricao")
                .setCategoria(CategoriaProduto.LANCHE)
                .setPreco(BigDecimal.valueOf(10))
                .setImagemPath("path");
    }
}
