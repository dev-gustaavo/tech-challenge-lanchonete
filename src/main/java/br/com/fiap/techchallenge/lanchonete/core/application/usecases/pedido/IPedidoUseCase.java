package br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido;

import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;

public interface IPedidoUseCase {
    Integer store(Pedido pedido);
}
