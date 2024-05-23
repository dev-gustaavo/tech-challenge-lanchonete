package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryPedido extends JpaRepository<PedidoEntity, Integer> {
    Optional<List<PedidoEntity>> findByStatusPagamento(String statusPagamento);
}
