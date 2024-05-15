package br.com.fiap.techchallenge.lanchonete.core.domain.repositories;

import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;

import java.util.List;

public interface IProdutoRepository {

    Produto save(Produto produto) throws Exception;
    Produto update(Integer id, Produto produto) throws Exception;
    void delete(Integer id) throws Exception;
    List<Produto> buscaPorCategoria(String categoria) throws Exception;
}
