package br.com.fiap.techchallenge.lanchonete.core.domain.repositories;

import br.com.fiap.techchallenge.lanchonete.core.domain.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;

import java.util.List;

public interface IPedidoRepository {
    Integer store(Pedido pedido);
    List<Pedido> listarPedidosPagos() throws Exception;
    Pedido buscarPorNumeroPedido(Integer numeroPedido) throws Exception;
    void atualizarStatusPagamento(Integer id, Pedido pedido, StatusPagamento statusPagamento) throws Exception;
    void atualizarEtapaPedido(Integer id, Pedido pedido, EtapaPedido etapaPedido) throws Exception;
}
