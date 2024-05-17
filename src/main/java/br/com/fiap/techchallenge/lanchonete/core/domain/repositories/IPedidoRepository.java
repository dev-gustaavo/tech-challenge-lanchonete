package br.com.fiap.techchallenge.lanchonete.core.domain.repositories;

import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;

public interface IPedidoRepository {
    Integer store(Pedido pedido);
}
