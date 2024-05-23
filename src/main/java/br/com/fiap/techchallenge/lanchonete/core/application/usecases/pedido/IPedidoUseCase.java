package br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido;

import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;

import java.util.List;

public interface IPedidoUseCase {
    Integer store(Pedido pedido) throws Exception;
    Boolean efetuarPagamento(Integer numeroPedido) throws Exception;
    List<Pedido> listarPedidosPagos() throws Exception;
}
