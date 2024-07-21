package br.com.fiap.techchallenge.lanchonete.interfaces.gateways;

import br.com.fiap.techchallenge.lanchonete.entities.Produto;

import java.util.List;

public interface ProdutoGateway {

    Produto save(Produto produto) throws Exception;
    Produto update(Produto produto) throws Exception;
    void delete(Integer id) throws Exception;
    List<Produto> buscaPorCategoria(String categoria) throws Exception;
    Boolean isProduto(Integer id);
}
