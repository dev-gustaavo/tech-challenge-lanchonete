package br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.MercadoPago;
import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido.PedidoRepository;
import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.produto.ProdutoRepository;
import br.com.fiap.techchallenge.lanchonete.core.domain.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.core.domain.exception.ProdutoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoUseCase implements IPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final MercadoPago mercadoPago;

    @Override
    public Integer store(Pedido pedido) {

        pedido.getProdutoId().forEach(produtoId -> {
            var produtoExiste = produtoRepository.isProduto(produtoId);

            if (!produtoExiste)
                throw new ProdutoNotFoundException("Verifique os produtos adicionados, existem produtos não cadastrados.");
        });

        return pedidoRepository.store(pedido);
    }

    @Override
    public Boolean efetuarPagamento(Integer numeroPedido) throws Exception {
        var isPago = mercadoPago.efetuarPagamento(numeroPedido);

        if (isPago) {
            var pedido = pedidoRepository.buscarPorNumeroPedido(numeroPedido);
            pedidoRepository.atualizarStatusPagamento(numeroPedido, pedido, StatusPagamento.PAGO);
            pedidoRepository.atualizarEtapaPedido(numeroPedido, pedido, EtapaPedido.EM_PREPARACAO);
            return true;
        }

        return false;
    }

    @Override
    public List<Pedido> listarPedidosPagos() throws Exception {
        return pedidoRepository.listarPedidosPagos();
    }
}
