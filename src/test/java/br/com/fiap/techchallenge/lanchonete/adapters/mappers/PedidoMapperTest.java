package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoDTOMock;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoMock;
import br.com.fiap.techchallenge.lanchonete.mocks.WebhookDTOMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoMapperTest {

    private PedidoMapper pedidoMapper = PedidoMapper.INSTANCE;
    private final PedidoDTO pedidoDTOMock = PedidoDTOMock.getPedidoDto();
    private final PedidoDTO pedidoDTOListaProdutoNull = PedidoDTOMock.getPedidoDtoListaProdutoNull();
    private final Pedido pedidoMock = PedidoMock.getPedido();
    private final PedidoEntity pedidoEntityMock = PedidoEntityMock.getPedidoEntity();
    private final PedidoEntity pedidoEntityMockAllNull = PedidoEntityMock.getPedidoEntityListaProdutoStatusPagamentoEtapaPedidoNull();
    private final PedidoEntity pedidoEntityStatusPagamentoNullMock = PedidoEntityMock.getPedidoEntityStatusPagamentoNull();
    private final PedidoEntity pedidoEntityListaProdutoNullMock = PedidoEntityMock.getPedidoEntityListaProdutoNull();

    private final Pedido pedidoEtapaPedidoNullMock = PedidoMock.getPedidoEtapaPedidoNull();
    private final Pedido pedidoStatusPagamentoNullMock = PedidoMock.getPedidoStatusPagamentoNull();
    private final Pedido pedidoListaProdutoNullMock = PedidoMock.getPedidoListaProdutoNull();
    private final WebhookDTO webhookDTOMock = WebhookDTOMock.getWebhookDto();

    @Test
    @Description("Deve retornar null o DTO for null")
    void deveRetornarNullQuandoDtoNull() {
        assertNull(pedidoMapper.toEntity(null));
    }

    @Test
    @Description("Deve retornar o objeto Pedido")
    void deveRetornarObjetoPedido() {
        var result = pedidoMapper.toEntity(pedidoDTOMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoDTOMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getProdutoId().size(), pedidoDTOMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoDTOMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar o objeto Pedido com a lista de produto null")
    void deveRetornarObjetoPedidoListaProdutoNull() {
        var result = pedidoMapper.toEntity(pedidoDTOListaProdutoNull);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoDTOMock.getIdentificacaoCliente()),
                () -> assertNull(result.getProdutoId())
        );
    }

    @Test
    @Description("Deve retornar null quando Pedido for null")
    void deveRetornarNullQuandoPedidoForNull() {
        assertNull(pedidoMapper.toDbEntity(null));
    }

    @Test
    @Description("Deve retornar Pedido Entity")
    void deveRetornarPedidoEntity() {
        var result = pedidoMapper.toDbEntity(pedidoMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Etapa Pedido RECEBIDO")
    void deveRetornarEtapaPedidoNull() {
        var result = pedidoMapper.toDbEntity(pedidoEtapaPedidoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), EtapaPedido.RECEBIDO.toString()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Status Pagamento PENDENTE")
    void deveRetornarStatusPagamentoNull() {
        var result = pedidoMapper.toDbEntity(pedidoStatusPagamentoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name()),
                () -> assertEquals(result.getStatusPagamento(), StatusPagamento.PENDENTE.toString()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Lista Produto null")
    void deveRetornarListaProdutoNull() {
        var result = pedidoMapper.toDbEntity(pedidoListaProdutoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertNull(result.getProdutoId())
        );
    }



    @Test
    @Description("Deve retornar null quando Pedido Entity for null")
    void deveRetornarNullQuandoPedidoEntityForNull() {
        assertNull(pedidoMapper.fromDbEntityToEntity(null));
    }

    @Test
    @Description("Deve retornar Pedido")
    void deveRetornarClienteEntity() {
        var result = pedidoMapper.toDbEntity(pedidoMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertEquals(result.getProdutoId().size(), pedidoMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar o objeto Pedido com Etapa Pedido RECEBIDO")
    void deveRetornarObjetoPedidoComEtapaPedidoNull() {
        var result = pedidoMapper.fromDbEntityToEntity(pedidoEntityMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoEntityMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), EtapaPedido.RECEBIDO),
                () -> assertEquals(result.getStatusPagamento().name(), pedidoEntityMock.getStatusPagamento()),
                () -> assertEquals(result.getProdutoId().size(), pedidoEntityMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoEntityMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar o objeto Pedido com Status Pagamento PENDENTE")
    void deveRetornarObjetoPedidoComStatusPagamentoNull() {
        var result = pedidoMapper.fromDbEntityToEntity(pedidoEntityStatusPagamentoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoEntityStatusPagamentoNullMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido().name(), pedidoEntityStatusPagamentoNullMock.getEtapaPedido()),
                () -> assertEquals(result.getStatusPagamento(), StatusPagamento.PENDENTE),
                () -> assertEquals(result.getProdutoId().size(), pedidoEntityStatusPagamentoNullMock.getProdutoId().size()),
                () -> assertEquals(result.getProdutoId().get(0), pedidoEntityStatusPagamentoNullMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar o objeto Pedido com Lista Produto null")
    void deveRetornarObjetoPedidoComListaProdutoNull() {
        var result = pedidoMapper.fromDbEntityToEntity(pedidoEntityListaProdutoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoEntityListaProdutoNullMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido().name(), pedidoEntityListaProdutoNullMock.getEtapaPedido()),
                () -> assertEquals(result.getStatusPagamento().name(), pedidoEntityListaProdutoNullMock.getStatusPagamento()),
                () -> assertNull(result.getProdutoId())
        );
    }

    @Test
    @Description("Deve retornar o objeto pedido com Lista Produto Null Status Pagamento PENDENTE e Etapa Pedido RECEBIDO")
    void deveRetornarPedidoListaProdutoNullStatusPagamentoPENDENTEEtapaPedidoRECEBIDO() {
        var result = pedidoMapper.fromDbEntityToEntity(pedidoEntityMockAllNull);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoEntityListaProdutoNullMock.getIdentificacaoCliente()),
                () -> assertEquals(result.getEtapaPedido(), EtapaPedido.RECEBIDO),
                () -> assertEquals(result.getStatusPagamento(), StatusPagamento.PENDENTE),
                () -> assertNull(result.getProdutoId())
        );
    }

    @Test
    @Description("Deve retornar o objeto PedidoResponse")
    void deveRetornarObjetoPedidoResponse() {
        var result = pedidoMapper.toResponse(pedidoMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getCodigo(), pedidoMock.getNumero()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name())
        );
    }

    @Test
    @Description("Deve retornar null quando Pedido for null")
    void deveRetornarNullQuandoPedidoForNullParaPedidoResponse() {
        assertNull(pedidoMapper.toResponse(null));
    }

    @Test
    @Description("Deve retornar PedidoResponse com status pagamento null")
    void deveRetornarPedidoResponseComStatusPagamentoNull() {
        var result = pedidoMapper.toResponse(pedidoStatusPagamentoNullMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getCodigo(), pedidoMock.getNumero()),
                () -> assertNull(result.getStatusPagamento()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido().name())
        );
    }

    @Test
    @Description("Deve retornar PedidoResponse com etapa pedido null")
    void deveRetornarPedidoResponseComEtapaPedidoNull() {
        var result = pedidoMapper.toResponse(pedidoEtapaPedidoNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getCodigo(), pedidoMock.getNumero()),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento().name()),
                () -> assertNull(result.getEtapaPedido())
        );
    }

    @Test
    @Description("Deve retornar null quando WebhookDTO for null")
    void deveRetornarNullQuandoWebhookDtoForNull() {
        assertNull(pedidoMapper.fromWebhookToPedido(null));
    }

    @Test
    @Description("Deve retornar objeto Pedido a partir de WebhookDTO")
    void deveRetornarObjetoPedidoApartirDeWebhookDTO() {
        var result = pedidoMapper.fromWebhookToPedido(webhookDTOMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNumero(), webhookDTOMock.getNumeroPedido()),
                () -> assertEquals(result.getStatusPagamento(), webhookDTOMock.getStatusPagamento())
        );
    }

    @Test
    @Description("Deve retornar null quando StatusPagamento e EtapaPedido forem null")
    void deveRetornarNullStatusPagamentoEtapaPedidoNull() {
        assertNull(pedidoMapper.toStatusPagoAndEtapaPedidoEmPreparacao(null, null));
    }

    @Test
    @Description("Deve retornar PedidoEntity quando StatusPagamento e EtapaPedido preenchidos")
    void deveRetornarPedidoEntityStatusPagamentoEtapaPedidoPreenchidos() {
        var result = pedidoMapper.toStatusPagoAndEtapaPedidoEmPreparacao(
                StatusPagamento.PAGO, EtapaPedido.EM_PREPARACAO
        );

        assertAll(
                () -> assertEquals(result.getStatusPagamento(), StatusPagamento.PAGO.toString()),
                () -> assertEquals(result.getEtapaPedido(), EtapaPedido.EM_PREPARACAO.toString())
        );
    }
}
