package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.*;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.MercadoPagoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.PedidoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.PedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final MercadoPagoGateway mercadoPagoGateway;

    @Override
    public Pedido store(Pedido pedido) throws Exception {

        try {
            pedido.getProdutoId().forEach(produtoId -> {
                var produtoExiste = produtoGateway.isProduto(produtoId);

                if (!produtoExiste)
                    throw new ProdutoException(MensagemErroPadrao.PRODUTO_PEDIDO_NAO_ENCONTRADO);
            });

            return pedidoGateway.store(pedido);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    @Override
    public QrCode gerarQrCode(Integer numeroPedido) {
        return mercadoPagoGateway.gerarQrCode(numeroPedido);
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        return pedidoGateway.listarPedidos();
    }

    @Override
    public Pedido notificacaoPagamento(Pedido pedido) throws Exception {
        try {
            if (pedido.getStatusPagamento().equals(StatusPagamento.PAGO)) {
                return pedidoGateway.atualizarStatusPagamentoParaPago(pedido);
            } else {
                return pedido;
            }
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    @Override
    public Pedido consultarStatusPagamento(Integer numeroPedido) throws Exception {
        return pedidoGateway.consultarStatusPagamento(numeroPedido);
    }
}
