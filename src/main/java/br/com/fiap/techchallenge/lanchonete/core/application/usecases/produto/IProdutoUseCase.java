package br.com.fiap.techchallenge.lanchonete.core.application.usecases.produto;

import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;

import java.util.List;

public interface IProdutoUseCase {

    Produto save(Produto produto) throws Exception;
    Produto update(Integer id, Produto produto) throws Exception;
    void delete(int id) throws Exception;

    List<Produto> buscaProdutoPorCategoria(String categoria) throws Exception;
}
