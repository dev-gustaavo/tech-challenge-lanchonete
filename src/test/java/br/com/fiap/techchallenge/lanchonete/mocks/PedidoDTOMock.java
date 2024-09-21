package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;

import java.util.List;

public class PedidoDTOMock {

    public static PedidoDTO getPedidoDto() {
        return new PedidoDTO()
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(List.of(1, 2));
    }

    public static PedidoDTO getPedidoDtoListaProdutoNull() {
        return new PedidoDTO()
                .setIdentificacaoCliente("01234567890")
                .setProdutoId(null);
    }
}
