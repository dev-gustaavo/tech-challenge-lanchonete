package br.com.fiap.techchallenge.lanchonete.interfaces.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;

import java.util.List;

public interface PedidoUseCase {
    Pedido store(Pedido pedido) throws Exception;
    QrCode gerarQrCode(Integer numeroPedido) throws Exception;
    List<Pedido> listarPedidos() throws Exception;
    Pedido notificacaoPagamento(Pedido pedido) throws Exception;
    Pedido consultarStatusPagamento(Integer numeroPedido) throws Exception;
}
