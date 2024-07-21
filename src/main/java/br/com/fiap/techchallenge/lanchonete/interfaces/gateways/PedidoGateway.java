package br.com.fiap.techchallenge.lanchonete.interfaces.gateways;

import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;

import java.util.List;

public interface PedidoGateway {
    Pedido store(Pedido pedido) throws Exception;
    List<Pedido> listarPedidos() throws Exception;
    Pedido atualizarStatusPagamentoParaPago(Pedido pedido) throws Exception;
    Pedido consultarStatusPagamento(Integer numeroPedido) throws Exception;
}
