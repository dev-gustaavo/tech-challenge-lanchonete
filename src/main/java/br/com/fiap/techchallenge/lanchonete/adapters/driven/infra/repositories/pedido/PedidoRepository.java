package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.PedidoEntity;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IPedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PedidoRepository implements IPedidoRepository {

    private final RepositoryPedido repositoryPedido;
    private final ObjectMapper mapper;


    public Integer store(Pedido pedido) {
        var pedidoEntity = mapper.convertValue(pedido, PedidoEntity.class);
        pedidoEntity = repositoryPedido.save(pedidoEntity);

        return pedidoEntity.getId();
    }
}
