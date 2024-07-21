package br.com.fiap.techchallenge.lanchonete.interfaces.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.Produto;

import java.util.List;

public interface ProdutoUseCase {

    Produto save(Produto produto) throws Exception;
    Produto update(Produto produto) throws Exception;
    void delete(int id) throws Exception;
    List<Produto> buscaProdutoPorCategoria(String categoria) throws Exception;
}
