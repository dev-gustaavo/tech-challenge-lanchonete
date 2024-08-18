package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;

import java.util.List;

public class PedidoMock {

    public static Pedido getPedido() {
        return new Pedido()
                .setEtapaPedido(EtapaPedido.RECEBIDO)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of(1, 2))
                .setStatusPagamento(StatusPagamento.PENDENTE);
    }

    public static Pedido getPedidoEtapaPedidoNull() {
        return new Pedido()
                .setEtapaPedido(null)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of(1, 2))
                .setStatusPagamento(StatusPagamento.PENDENTE);
    }

    public static Pedido getPedidoStatusPagamentoNull() {
        return new Pedido()
                .setEtapaPedido(EtapaPedido.RECEBIDO)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of(1, 2))
                .setStatusPagamento(null);
    }

    public static Pedido getPedidoListaProdutoNull() {
        return new Pedido()
                .setEtapaPedido(EtapaPedido.RECEBIDO)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(null)
                .setStatusPagamento(StatusPagamento.PENDENTE);
    }

    public static Pedido getPedidoListaProdutoVazia() {
        return new Pedido()
                .setEtapaPedido(EtapaPedido.RECEBIDO)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of())
                .setStatusPagamento(StatusPagamento.PENDENTE);
    }

    public static Pedido getPedidoPago() {
        return new Pedido()
                .setEtapaPedido(EtapaPedido.RECEBIDO)
                .setNumero(1)
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of(1, 2))
                .setStatusPagamento(StatusPagamento.PAGO);
    }
}
