package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;


    @Override
    public Produto save(Produto produto) throws Exception {
        return produtoGateway.save(produto);
    }

    @Override
    public Produto update(Produto produto) throws Exception {
        if (produto.getId() != null)
            return produtoGateway.update(produto);

        throw new ProdutoException(MensagemErroPadrao.ATUALIZACAO_PRODUTO_SEM_ID);
    }

    @Override
    public void delete(int id) throws Exception {
        produtoGateway.delete(id);
    }

    @Override
    public List<Produto> buscaProdutoPorCategoria(String categoria) throws Exception {
        return produtoGateway.buscaPorCategoria(categoria);
    }
}
