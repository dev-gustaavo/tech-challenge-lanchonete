package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.PedidoMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.outbound.PedidoResponse;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.PedidoUseCase;
import br.com.fiap.techchallenge.lanchonete.mocks.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoControllerTest {

    @Mock
    private PedidoUseCase pedidoUseCase;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoController pedidoController;

    private final Pedido pedidoMock = PedidoMock.getPedido();
    private final PedidoDTO pedidoDTOMock = PedidoDTOMock.getPedidoDto();
    private final PedidoResponse pedidoResponseMock = PedidoResponseMock.getPedidoResponse();
    private final QrCode qrCodeMock = QrCodeMock.getQrCode();
    private final WebhookDTO webhookDTOMock = WebhookDTOMock.getWebhookDto();

    @Test
    @Description("Deve criar um pedido com sucesso")
    void criarClienteTest() throws Exception {
        when(pedidoMapper.toEntity(any())).thenReturn(pedidoMock);
        when(pedidoUseCase.store(any())).thenReturn(pedidoMock);
        when(pedidoMapper.toResponse(any())).thenReturn(pedidoResponseMock);

        var result = pedidoController.criaPedido(pedidoDTOMock);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.CREATED.value()),
                () -> assertEquals(body.getCodigo(), pedidoResponseMock.getCodigo()),
                () -> assertEquals(body.getEtapaPedido(), pedidoResponseMock.getEtapaPedido()),
                () -> assertEquals(body.getStatusPagamento(), pedidoResponseMock.getStatusPagamento())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar cadastrar um pedido")
    void deveRetornarExceptionQuandoTentaCadastrarPedido() throws Exception {
        when(pedidoMapper.toEntity(any())).thenReturn(pedidoMock);
        when(pedidoUseCase.store(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> pedidoController.criaPedido(pedidoDTOMock));
    }

    @Test
    @Description("Deve gerar um QR code para pagamento do pedido")
    void deveGerarQrCodeParaPagamento() throws Exception {
        when(pedidoUseCase.gerarQrCode(any())).thenReturn(qrCodeMock);

        var result = pedidoController.gerarQrCode(1);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertNotNull(result),
                () -> assertEquals(body.getCodigo(), qrCodeMock.getCodigo())
        );
    }

    @Test
    @Description("Deve retornar uma exception ao tentar gerar um QR code para pagamento do pedido")
    void deveRetornarExceptionAoTentarGerarQrCodeParaPagamento() throws Exception {
        when(pedidoUseCase.gerarQrCode(any())).thenThrow(new Exception("erro"));
        assertThrows(Exception.class, () -> pedidoController.gerarQrCode(1));
    }

    @Test
    @Description("Deve consultar o status de pagamento do pedido através do código do pedido")
    void deveConsultarOStatusDePagamentoDoPedidoAtravesDoCodigo() throws Exception {
        when(pedidoUseCase.consultarStatusPagamento(any())).thenReturn(pedidoMock);
        when(pedidoMapper.toResponse(any())).thenReturn(pedidoResponseMock);

        var result = pedidoController.consultarStatusPagamento(1);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertNotNull(result),
                () -> assertEquals(body.getStatusPagamento(), pedidoResponseMock.getStatusPagamento())
        );
    }

    @Test
    @Description("Deve retornar uma exception ao tentar consultar o status de pagamento do pedido através do código do pedido")
    void deveRetornarExceptionAoConsultarOStatusDePagamentoDoPedidoAtravesDoCodigo() throws Exception {
        when(pedidoUseCase.consultarStatusPagamento(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> pedidoController.consultarStatusPagamento(1));
    }

    @Test
    @Description("Deve receber uma notificação de pagamento")
    void deveReceberUmaNotificacaoDePagamento() throws Exception {
        when(pedidoMapper.fromWebhookToPedido(any())).thenReturn(pedidoMock);
        when(pedidoUseCase.notificacaoPagamento(any())).thenReturn(pedidoMock);
        when(pedidoMapper.toResponse(any())).thenReturn(pedidoResponseMock);

        var result = pedidoController.notificarPagamento(webhookDTOMock);

        var body = result.getBody();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(body.getStatusPagamento(), pedidoResponseMock.getStatusPagamento()),
                () -> assertEquals(body.getCodigo(), pedidoResponseMock.getCodigo()),
                () -> assertEquals(body.getEtapaPedido(), pedidoResponseMock.getEtapaPedido())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar notificar o pagamento")
    void deveRetornarUmExceptionQuandoTentarNotificarOPagamento() throws Exception {
        when(pedidoMapper.fromWebhookToPedido(any())).thenReturn(pedidoMock);
        when(pedidoUseCase.notificacaoPagamento(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> pedidoController.notificarPagamento(webhookDTOMock));
    }

    @Test
    @Description("Deve retornar uma lista de pedidos")
    void deveRetornarUmaListaDePedidos() throws Exception {
        when(pedidoUseCase.listarPedidos()).thenReturn(List.of(pedidoMock));

        var result = pedidoController.listarPedidos();
        var body = result.getBody();
        var item = body.get(0);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(body.size(), 1),
                () -> assertEquals(item.getEtapaPedido(), pedidoMock.getEtapaPedido()),
                () -> assertEquals(item.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(item.getNumero(), pedidoMock.getNumero()),
                () -> assertEquals(item.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(item.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }
}
