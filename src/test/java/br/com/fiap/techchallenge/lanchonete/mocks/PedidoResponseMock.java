package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.dtos.outbound.PedidoResponse;

public class PedidoResponseMock {

    public static PedidoResponse getPedidoResponse() {
        return new PedidoResponse()
                .setCodigo(1)
                .setEtapaPedido("EM_PREPARACAO")
                .setStatusPagamento("PENDENTE");
    }
}
