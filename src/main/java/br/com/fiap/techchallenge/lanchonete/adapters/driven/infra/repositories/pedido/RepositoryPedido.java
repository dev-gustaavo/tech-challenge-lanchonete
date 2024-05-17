package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPedido extends JpaRepository<PedidoEntity, Integer> {
}
