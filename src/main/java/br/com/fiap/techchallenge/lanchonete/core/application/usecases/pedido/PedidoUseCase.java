package br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido.PedidoRepository;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoUseCase implements IPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    @Override
    public Integer store(Pedido pedido) {
        return pedidoRepository.store(pedido);
    }
}
