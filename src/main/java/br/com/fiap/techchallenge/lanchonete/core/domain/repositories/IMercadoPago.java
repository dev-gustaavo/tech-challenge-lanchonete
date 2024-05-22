package br.com.fiap.techchallenge.lanchonete.core.domain.repositories;

public interface IMercadoPago {
    Boolean efetuarPagamento(Integer numeroPedido);
}
