package br.com.fiap.techchallenge.lanchonete.adapters.driver.controllers;

import br.com.fiap.techchallenge.lanchonete.adapters.inbound.dto.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.adapters.outbound.dto.PedidoResponse;
import br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido.IPedidoUseCase;
import br.com.fiap.techchallenge.lanchonete.core.domain.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final IPedidoUseCase pedidoUseCase;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<PedidoResponse> criaPedido(@RequestBody @Valid PedidoDTO pedidoRequest) throws Exception {
        var pedidoDomain = mapper.convertValue(pedidoRequest, Pedido.class);
        var numeroPedido = pedidoUseCase.store(pedidoDomain);

        return ResponseEntity.ok(new PedidoResponse(numeroPedido, "Pedido cadastrado com sucesso.",
                StatusPagamento.PENDENTE.toString(), EtapaPedido.RECEBIDO.toString()));
    }

    @PostMapping("/pagar/{numeroPedido}")
    public ResponseEntity<PedidoResponse> efetuaPagamento(@PathVariable(value = "numeroPedido") Integer numeroPedido) throws Exception {
        pedidoUseCase.efetuarPagamento(numeroPedido);
        return ResponseEntity.ok(new PedidoResponse(numeroPedido,"Pagamento efetuado com sucesso.",
                StatusPagamento.PAGO.toString(), EtapaPedido.EM_PREPARACAO.toString()));
    }

    @GetMapping("/listar_pagos")
    public ResponseEntity<List<Pedido>> listarPedidosPagos() throws Exception {
        return ResponseEntity.ok(pedidoUseCase.listarPedidosPagos());
    }
}
