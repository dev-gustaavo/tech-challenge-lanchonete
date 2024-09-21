package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;

import java.util.List;

public class PedidoEntityMock {

    public static PedidoEntity getPedidoEntity() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(EtapaPedido.RECEBIDO.toString())
                .setStatusPagamento(StatusPagamento.PENDENTE.toString())
                .setProdutoId(List.of(1));
    }

    public static PedidoEntity getPedidoEntityPago() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(EtapaPedido.RECEBIDO.toString())
                .setStatusPagamento(StatusPagamento.PAGO.toString())
                .setProdutoId(List.of(1));
    }

    public static PedidoEntity getPedidoEntityEtapaPedidoNull() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(null)
                .setStatusPagamento(StatusPagamento.PENDENTE.toString())
                .setProdutoId(List.of(1));
    }

    public static PedidoEntity getPedidoEntityStatusPagamentoNull() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(EtapaPedido.RECEBIDO.toString())
                .setStatusPagamento(null)
                .setProdutoId(List.of(1));
    }

    public static PedidoEntity getPedidoEntityListaProdutoNull() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(EtapaPedido.RECEBIDO.toString())
                .setStatusPagamento(StatusPagamento.PENDENTE.toString())
                .setProdutoId(null);
    }

    public static PedidoEntity getPedidoEntityListaProdutoStatusPagamentoEtapaPedidoNull() {
        return new PedidoEntity()
                .setIdentificacaoCliente("01234567890")
                .setEtapaPedido(null)
                .setStatusPagamento(null)
                .setProdutoId(null);
    }
}
