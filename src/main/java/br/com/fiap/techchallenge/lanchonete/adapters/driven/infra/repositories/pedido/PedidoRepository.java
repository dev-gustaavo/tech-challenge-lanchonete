package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.pedido;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.PedidoEntity;
import br.com.fiap.techchallenge.lanchonete.core.domain.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;
import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IPedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Pedido> listarPedidosPagos() throws Exception {

        try {
            var pedidosPagosOptional = repositoryPedido
                    .findByStatusPagamento(StatusPagamento.PAGO.toString());

            if (pedidosPagosOptional.isPresent()) return pedidosPagosOptional.get()
                    .stream()
                    .map(pedidoPago -> mapper.convertValue(pedidoPago, Pedido.class))
                    .collect(Collectors.toList());

            throw new EntityNotFoundException(String.format("Nenhum pedido com o status %s", StatusPagamento.PAGO.toString()));
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getLocalizedMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public Pedido buscarPorNumeroPedido(Integer numeroPedido) throws Exception {
        try {
            var pedidoOptional = repositoryPedido.findById(numeroPedido);

            if (pedidoOptional.isPresent())
                return mapper.convertValue(pedidoOptional, Pedido.class);

            throw new EntityNotFoundException(String.format("Não foi encontrado pedido com o número %s", numeroPedido));
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getLocalizedMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }

    }

    @Override
    public void atualizarStatusPagamento(Integer id, Pedido pedido, StatusPagamento statusPagamento) throws Exception {
        pedido.setStatusPagamento(statusPagamento);
        updatePedido(id, pedido);
    }

    @Override
    public void atualizarEtapaPedido(Integer id, Pedido pedido, EtapaPedido etapaPedido) throws Exception {
        pedido.setEtapaPedido(etapaPedido);
        updatePedido(id, pedido);
    }

    private void updatePedido(Integer id, Pedido pedido) throws Exception {
        try {
            var pedidoEntity = mapper.convertValue(pedido, PedidoEntity.class);
            pedidoEntity.setId(id);
            repositoryPedido.save(pedidoEntity);
        } catch (Exception exception) {
            throw new Exception("Erro ao atualizar etapa do pedido.");
        }
    }
}
