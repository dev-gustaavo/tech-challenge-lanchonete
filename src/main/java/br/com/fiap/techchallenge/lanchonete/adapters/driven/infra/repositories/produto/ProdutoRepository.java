package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.produto;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.ProdutoEntity;
import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IProdutoRepository;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProdutoRepository implements IProdutoRepository {

    private final RepositoryProduto repositoryProduto;
    private final ObjectMapper mapper;


    @Override
    public Produto save(Produto produto) throws Exception {
        try {
            var produtoEntity = mapper.convertValue(produto, ProdutoEntity.class);
            produtoEntity = repositoryProduto.save(produtoEntity);

            return mapper.convertValue(produtoEntity, Produto.class);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public Produto update(Integer id, Produto produto) throws Exception {
        try {
            var produtoOptional = repositoryProduto.findById(id);

            if (produtoOptional.isPresent())
                return aplicaAlteracoes(produto, produtoOptional);

            throw new EntityNotFoundException("Produto não encontrado para alteração.");
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    private Produto aplicaAlteracoes(Produto produto, Optional<ProdutoEntity> produtoEntityOptional) {
        var produtoEntity = produtoEntityOptional.get();

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setCategoria(produto.getCategoria().toString());
        produtoEntity.setPreco(produto.getPreco());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setImagemPath(produto.getImagemPath());

        return mapper.convertValue(repositoryProduto.save(produtoEntity), Produto.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            repositoryProduto.deleteById(id);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Produto> buscaPorCategoria(String categoria) throws Exception {
        try {
            var produtoEntityOptional = repositoryProduto.findByCategoria(categoria);

            if (produtoEntityOptional.isPresent()) {
                var produtos = produtoEntityOptional.get()
                        .stream()
                        .map(produtoEntity -> mapper.convertValue(produtoEntity, Produto.class))
                        .collect(Collectors.toList());
                return produtos;
            }

            throw new EntityNotFoundException(String.format("Nenhum produto encontrado com a categoria %s", categoria));
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getLocalizedMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }
}

