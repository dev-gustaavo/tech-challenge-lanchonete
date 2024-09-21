package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.PedidoMapper;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection.RepositoryPedido;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoMock;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoGatewayImplTest {

    @Mock
    private RepositoryPedido repositoryPedido;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    private final PedidoEntity pedidoEntityMock = PedidoEntityMock.getPedidoEntity();
    private final PedidoEntity pedidoEntityPagoMock = PedidoEntityMock.getPedidoEntityPago();
    private final Pedido pedidoMock = PedidoMock.getPedido();
    private final Pedido pedidoPagoMock = PedidoMock.getPedidoPago();

    @Test
    @Description("Deve salvar um pedido no banco de dados")
    void deveSalvarPedidoBancoDados() throws Exception {
        when(pedidoMapper.toDbEntity(any())).thenReturn(pedidoEntityMock);
        when(repositoryPedido.save(any())).thenReturn(pedidoEntityMock);
        when(pedidoMapper.fromDbEntityToEntity(any())).thenReturn(pedidoMock);

        var result = pedidoGateway.store(pedidoMock);
        var item = result.getProdutoId().get(0);

        verify(pedidoMapper, times(1)).toDbEntity(any());
        verify(repositoryPedido, times(1)).save(any());
        verify(pedidoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNumero(), pedidoMock.getNumero()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(item, pedidoMock.getProdutoId().get(0)),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar salvar um pedido")
    void deveRetornarExceptionSalvarPedido() {
        when(pedidoMapper.toDbEntity(any())).thenReturn(pedidoEntityMock);
        when(repositoryPedido.save(any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pedidoGateway.store(pedidoMock));
        verify(pedidoMapper, times(1)).toDbEntity(any());
        verify(repositoryPedido, times(1)).save(any());
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve listar pedido pagos")
    void deveListarPedidosPagos() throws Exception {
        when(repositoryPedido.listPedidosOrdenadoPorStatus()).thenReturn(Optional.of(List.of(pedidoEntityPagoMock)));
        when(pedidoMapper.fromDbEntityToEntity(any())).thenReturn(pedidoPagoMock);

        var result = pedidoGateway.listarPedidos();
        var item = result.get(0).getProdutoId().get(0);

        verify(repositoryPedido, times(1)).listPedidosOrdenadoPorStatus();
        verify(pedidoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.get(0).getNumero(), pedidoPagoMock.getNumero()),
                () -> assertEquals(result.get(0).getProdutoId().size(), pedidoPagoMock.getProdutoId().size()),
                () -> assertEquals(item, pedidoPagoMock.getProdutoId().get(0)),
                () -> assertEquals(result.get(0).getEtapaPedido(), pedidoPagoMock.getEtapaPedido()),
                () -> assertEquals(result.get(0).getStatusPagamento(), StatusPagamento.PAGO),
                () -> assertEquals(result.get(0).getIdentificacaoCliente(), pedidoPagoMock.getIdentificacaoCliente())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException por ter a lista de pedidos vazia")
    void deveRetornarEntityNotFoundExceptionListaPedidoVazia() {
        when(repositoryPedido.listPedidosOrdenadoPorStatus()).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoGateway.listarPedidos());
        verify(repositoryPedido, times(1)).listPedidosOrdenadoPorStatus();
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception genérica ao tentar listar pedidos pagos")
    void deveRetornarExceptionGenericaListarPedidosPagos() {
        when(repositoryPedido.listPedidosOrdenadoPorStatus()).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pedidoGateway.listarPedidos());
        verify(repositoryPedido, times(1)).listPedidosOrdenadoPorStatus();
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve atualizar status pagamento para pago")
    void deveAtualizarStatusPagamentoPago() throws Exception {
        when(repositoryPedido.findById(anyInt())).thenReturn(Optional.of(pedidoEntityMock));
        when(pedidoMapper.toStatusPagoAndEtapaPedidoEmPreparacao(any(), any())).thenReturn(pedidoEntityPagoMock);
        when(repositoryPedido.save(any())).thenReturn(pedidoEntityPagoMock);
        when(pedidoMapper.fromDbEntityToEntity(any())).thenReturn(pedidoPagoMock);

        var result = pedidoGateway.atualizarStatusPagamentoParaPago(pedidoPagoMock);
        var item = result.getProdutoId().get(0);

        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(1)).toStatusPagoAndEtapaPedidoEmPreparacao(any(), any());
        verify(repositoryPedido, times(1)).save(any());
        verify(pedidoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNumero(), pedidoPagoMock.getNumero()),
                () -> assertEquals(result.getProdutoId().size(), pedidoPagoMock.getProdutoId().size()),
                () -> assertEquals(item, pedidoPagoMock.getProdutoId().get(0)),
                () -> assertEquals(result.getEtapaPedido(), pedidoPagoMock.getEtapaPedido()),
                () -> assertEquals(result.getStatusPagamento(), pedidoPagoMock.getStatusPagamento()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoPagoMock.getIdentificacaoCliente())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException por não encontrar o pedido para atualização")
    void deveRetornarEntityNotFoundExceptionPedidoNaoEncontrado() {
        when(repositoryPedido.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pedidoGateway.atualizarStatusPagamentoParaPago(pedidoMock));
        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(0)).toStatusPagoAndEtapaPedidoEmPreparacao(any(), any());
        verify(repositoryPedido, times(0)).save(any());
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar atualizar status de pagamento para pago")
    void deveRetornarExceptionAtualizarStatusPagamentoPago() {
        when(repositoryPedido.findById(anyInt())).thenReturn(Optional.of(pedidoEntityMock));
        when(pedidoMapper.toStatusPagoAndEtapaPedidoEmPreparacao(any(), any())).thenReturn(pedidoEntityPagoMock);
        when(repositoryPedido.save(any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pedidoGateway.atualizarStatusPagamentoParaPago(pedidoMock));
        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(1)).toStatusPagoAndEtapaPedidoEmPreparacao(any(), any());
        verify(repositoryPedido, times(1)).save(any());
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve consultar o status de pagamento de um pedido")
    void deveConsultarStatusPagamentoPedido() throws Exception {
        when(repositoryPedido.findById(anyInt())).thenReturn(Optional.of(pedidoEntityMock));
        when(pedidoMapper.fromDbEntityToEntity(any())).thenReturn(pedidoMock);

        var result = pedidoGateway.consultarStatusPagamento(1);
        var item = result.getProdutoId().get(0);

        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNumero(), pedidoMock.getNumero()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(item, pedidoMock.getProdutoId().get(0)),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException ao tentar consultar status pagamento")
    void deveRetornarEntityNotFoundExceptionAoTentarConsultarStatusPagamento() {
        when(repositoryPedido.findById(anyInt())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> pedidoGateway.consultarStatusPagamento(1));
        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar consultar status pagamento")
    void deveRetornarExceptionAoTentarConsultarStatusPagamento() {
        when(repositoryPedido.findById(anyInt())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> pedidoGateway.consultarStatusPagamento(1));
        verify(repositoryPedido, times(1)).findById(anyInt());
        verify(pedidoMapper, times(0)).fromDbEntityToEntity(any());
    }
}
