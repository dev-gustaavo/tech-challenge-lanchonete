package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.PedidoMapper;
import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection.RepositoryPedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PedidoGatewayImpl implements br.com.fiap.techchallenge.lanchonete.interfaces.gateways.PedidoGateway {

    private final RepositoryPedido repositoryPedido;
    private final ObjectMapper mapper;
    private final PedidoMapper pedidoMapper;


    public Pedido store(Pedido pedido) throws Exception {
        try {
            var pedidoEntity = pedidoMapper.toDbEntity(pedido);
            pedidoEntity = repositoryPedido.save(pedidoEntity);
            return pedidoMapper.fromDbEntityToEntity(pedidoEntity);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {

        try {
            var pedidosPagosOptional = repositoryPedido.listPedidosOrdenadoPorStatus();

            if (pedidosPagosOptional.isPresent())
                return pedidosPagosOptional.get()
                    .stream()
                    .map(pedidoMapper::fromDbEntityToEntity)
                    .collect(Collectors.toList());

            throw new EntityNotFoundException(String.format("Nenhum pedido com o status %s", StatusPagamento.PAGO));
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getLocalizedMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public Pedido atualizarStatusPagamentoParaPago(Pedido pedido) throws Exception {
        try {
            var pedidoDbEntityOptional = repositoryPedido.findById(pedido.getNumero());

            var pedidoDbEntityAlterado = alteraStatusPagamentoParaPago(pedidoDbEntityOptional);

            return pedidoMapper.fromDbEntityToEntity(pedidoDbEntityAlterado);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(MensagemErroPadrao.PEDIDO_NAO_ENCONTRADO, entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    private PedidoEntity alteraStatusPagamentoParaPago(Optional<PedidoEntity> pedidoDbEntityOptional) {
        var pedidoDbEntity = pedidoDbEntityOptional.get();

        pedidoDbEntity.setStatusPagamento(StatusPagamento.PAGO.toString());
        pedidoDbEntity.setEtapaPedido(EtapaPedido.EM_PREPARACAO.toString());

        return repositoryPedido.save(pedidoDbEntity);
    }

    @Override
    public Pedido consultarStatusPagamento(Integer numeroPedido) throws Exception {
        try {
            var pedidoDbEntity = repositoryPedido.findById(numeroPedido);

            return pedidoMapper.fromDbEntityToEntity(pedidoDbEntity.get());
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(MensagemErroPadrao.PEDIDO_NAO_ENCONTRADO, entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }
}
