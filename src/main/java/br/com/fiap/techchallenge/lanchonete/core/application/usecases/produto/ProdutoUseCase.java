package br.com.fiap.techchallenge.lanchonete.core.application.usecases.produto;

import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoUseCase implements IProdutoUseCase {

    private final IProdutoRepository produtoRepository;


    @Override
    public Produto save(Produto produto) throws Exception {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Integer id, Produto produto) throws Exception {
        return produtoRepository.update(id, produto);
    }

    @Override
    public void delete(int id) throws Exception {
        produtoRepository.delete(id);
    }

    @Override
    public List<Produto> buscaProdutoPorCategoria(String categoria) throws Exception {
        return produtoRepository.buscaPorCategoria(categoria);
    }
}
