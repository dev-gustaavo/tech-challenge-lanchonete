package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.produto;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryProduto extends JpaRepository<ProdutoEntity, Integer> {

    Optional<List<ProdutoEntity>> findByCategoria(String categoria);
}
