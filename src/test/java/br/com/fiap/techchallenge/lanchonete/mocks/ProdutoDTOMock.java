package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.entities.CategoriaProduto;

import java.math.BigDecimal;

public class ProdutoDTOMock {

    public static ProdutoDTO getProdutoDto() {
        return new ProdutoDTO()
                .setId(1)
                .setNome("produto")
                .setCategoria(CategoriaProduto.LANCHE)
                .setPreco(BigDecimal.valueOf(10))
                .setDescricao("descricao")
                .setImagemPath("path");
    }
}
