package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.MercadoPagoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.PedidoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.mocks.PedidoMock;
import br.com.fiap.techchallenge.lanchonete.mocks.QrCodeMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class PedidoUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private MercadoPagoGateway mercadoPagoGateway;

    @Mock
    private Pedido pedido;

    @InjectMocks
    private PedidoUseCaseImpl pedidoUseCase;

    private final Pedido pedidoMock = PedidoMock.getPedido();
    private final Pedido pedidoMockListaVazia = PedidoMock.getPedidoListaProdutoVazia();
    private final Pedido pedidoPagoMock = PedidoMock.getPedidoPago();
    private final QrCode qrCodeMock = QrCodeMock.getQrCode();

    @Test
    @Description("Deve cadastrar um pedido")
    void deveCadastrarUmPedido() throws Exception {
        when(produtoGateway.isProduto(anyInt())).thenReturn(true);
        when(pedidoGateway.store(any())).thenReturn(pedidoMock);
        var qtdItens = pedidoMock.getProdutoId().size();

        var result = pedidoUseCase.store(pedidoMock);
        var item = result.getProdutoId().get(0);

        verify(produtoGateway, times(qtdItens)).isProduto(anyInt());
        verify(pedidoGateway, times(1)).store(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(result.getNumero(), pedidoMock.getNumero()),
                () -> assertEquals(result.getEtapaPedido(), pedidoMock.getEtapaPedido()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(item, pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve validar lista de produtos vazia")
    void deveValidarListaProdutoVazia() throws Exception {
        assertThrows(ProdutoException.class, () -> pedidoUseCase.store(pedidoMockListaVazia));

        verify(produtoGateway, times(0)).isProduto(anyInt());
        verify(pedidoGateway, times(0)).store(any());
    }

    @Test
    @Description("Deve validar produto inexistente")
    void deveValidarProdutoInexistente() throws Exception {
        when(produtoGateway.isProduto(anyInt())).thenReturn(false);

        assertThrows(ProdutoException.class, () -> pedidoUseCase.store(pedidoMock));

        verify(produtoGateway, times(2)).isProduto(anyInt());
        verify(pedidoGateway, times(0)).store(any());
    }

    @Test
    @Description("Deve retornar uma Exception ao tentar cadastrar um pedido")
    void deveRetornarExceptionAoTentarCadastrarPedido() throws Exception {
        when(produtoGateway.isProduto(anyInt())).thenReturn(true);
        when(pedidoGateway.store(any())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> pedidoUseCase.store(pedidoMock));

        verify(produtoGateway, times(2)).isProduto(anyInt());
        verify(pedidoGateway, times(1)).store(any());
    }

    @Test
    @Description("Deve gerar QR Code")
    void deveGerarQrCode() {
        when(mercadoPagoGateway.gerarQrCode(anyInt())).thenReturn(qrCodeMock);

        var result = pedidoUseCase.gerarQrCode(1);

        assertNotNull(result);
    }

    @Test
    @Description("Deve listar pedidos")
    void deveListarPedidos() throws Exception {
        when(pedidoGateway.listarPedidos()).thenReturn(List.of(pedidoMock));

        var result = pedidoUseCase.listarPedidos();
        var item = result.get(0);

        verify(pedidoGateway, times(1)).listarPedidos();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(item.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(item.getNumero(), pedidoMock.getNumero()),
                () -> assertEquals(item.getEtapaPedido(), pedidoMock.getEtapaPedido()),
                () -> assertEquals(item.getIdentificacaoCliente(), pedidoMock.getIdentificacaoCliente()),
                () -> assertEquals(item.getProdutoId().get(0), pedidoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar listar pedidos")
    void deveRetornarExceptionAoTentarListarPedidos() throws Exception {
        when(pedidoGateway.listarPedidos()).thenThrow(new Exception());

        assertThrows(Exception.class, () -> pedidoUseCase.listarPedidos());
        verify(pedidoGateway, times(1)).listarPedidos();
    }

    @Test
    @Description("Deve notificar pagamento")
    void deveNotificarPagamento() throws Exception {
        when(pedidoGateway.atualizarStatusPagamentoParaPago(any())).thenReturn(pedidoPagoMock);

        var result = pedidoUseCase.notificacaoPagamento(pedidoPagoMock);
        var item = result.getProdutoId().get(0);

        verify(pedidoGateway, times(1)).atualizarStatusPagamentoParaPago(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getStatusPagamento(), StatusPagamento.PAGO),
                () -> assertEquals(result.getNumero(), pedidoPagoMock.getNumero()),
                () -> assertEquals(result.getEtapaPedido(), pedidoPagoMock.getEtapaPedido()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoPagoMock.getIdentificacaoCliente()),
                () -> assertEquals(item, pedidoPagoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar pedido com status não pago")
    void deveRetornarPedidoComStatusNaoPago() throws Exception {
        var result = pedidoUseCase.notificacaoPagamento(pedidoMock);
        var item = result.getProdutoId().get(0);

        verify(pedidoGateway, times(0)).atualizarStatusPagamentoParaPago(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(result.getNumero(), pedidoPagoMock.getNumero()),
                () -> assertEquals(result.getEtapaPedido(), pedidoPagoMock.getEtapaPedido()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoPagoMock.getIdentificacaoCliente()),
                () -> assertEquals(item, pedidoPagoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar atualizar o status de pagamento")
    void deveRetornarExceptionAoTentarAtualizarStatusPagamento() throws Exception {
        when(pedidoGateway.atualizarStatusPagamentoParaPago(any())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> pedidoUseCase.notificacaoPagamento(pedidoPagoMock));
        verify(pedidoGateway, times(1)).atualizarStatusPagamentoParaPago(any());
    }

    @Test
    @Description("Deve consultar o status de pagamento")
    void deveConsultarStatusPagamento() throws Exception {
        when(pedidoGateway.consultarStatusPagamento(anyInt())).thenReturn(pedidoMock);

        var result = pedidoUseCase.consultarStatusPagamento(1);
        var item = result.getProdutoId().get(0);

        verify(pedidoGateway, times(1)).consultarStatusPagamento(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getStatusPagamento(), pedidoMock.getStatusPagamento()),
                () -> assertEquals(result.getNumero(), pedidoPagoMock.getNumero()),
                () -> assertEquals(result.getEtapaPedido(), pedidoPagoMock.getEtapaPedido()),
                () -> assertEquals(result.getIdentificacaoCliente(), pedidoPagoMock.getIdentificacaoCliente()),
                () -> assertEquals(item, pedidoPagoMock.getProdutoId().get(0))
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar consultar o status de pagamento")
    void deveRetornarExceptionAoTentarConsultarStatusPagamento() throws Exception {
        when(pedidoGateway.consultarStatusPagamento(anyInt())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> pedidoUseCase.consultarStatusPagamento(1));
        verify(pedidoGateway, times(1)).consultarStatusPagamento(any());
    }
}
