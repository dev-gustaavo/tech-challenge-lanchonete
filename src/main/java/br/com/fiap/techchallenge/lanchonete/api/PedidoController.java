package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.PedidoMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.dtos.outbound.PedidoResponse;
import br.com.fiap.techchallenge.lanchonete.entities.*;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.PedidoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoUseCase pedidoUseCase;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody @Valid PedidoDTO pedidoRequest) throws Exception {
        var pedidoEntity = pedidoMapper.toEntity(pedidoRequest);

        var pedidoCriado = pedidoUseCase.store(pedidoEntity);

        var response = pedidoMapper.toResponse(pedidoCriado);

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(response);
    }

    @PostMapping("/gerar/qr_code/{numeroPedido}")
    public ResponseEntity<QrCode> gerarQrCode(@PathVariable(value = "numeroPedido") Integer numeroPedido) throws Exception {
        var qrCode = pedidoUseCase.gerarQrCode(numeroPedido);

        return ResponseEntity.
                status(HttpStatus.OK.value())
                .body(qrCode);
    }

    @GetMapping("/consultar/status/pagamento/{numeroPedido}")
    public ResponseEntity<PedidoResponse> consultarStatusPagamento(@PathVariable(value = "numeroPedido") Integer numeroPedido) throws Exception {
        var pedido = pedidoUseCase.consultarStatusPagamento(numeroPedido);

        var response = pedidoMapper.toResponse(pedido);

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(response);
    }

    @PostMapping("/webhook/notificacao/pagamento")
    public ResponseEntity<PedidoResponse> notificarPagamento(@RequestBody @Valid WebhookDTO request) throws Exception {
        var pedidoEntity = pedidoMapper.fromWebhookToPedido(request);

        var pedido = pedidoUseCase.notificacaoPagamento(pedidoEntity);

        var response = pedidoMapper.toResponse(pedido);

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() throws Exception {
        return ResponseEntity.ok(pedidoUseCase.listarPedidos());
    }
}
