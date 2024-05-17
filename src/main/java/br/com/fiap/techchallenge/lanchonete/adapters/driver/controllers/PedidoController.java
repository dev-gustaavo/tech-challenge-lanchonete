package br.com.fiap.techchallenge.lanchonete.adapters.driver.controllers;

import br.com.fiap.techchallenge.lanchonete.adapters.dto.PedidoDTO;
import br.com.fiap.techchallenge.lanchonete.core.application.usecases.pedido.IPedidoUseCase;
import br.com.fiap.techchallenge.lanchonete.core.domain.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final IPedidoUseCase pedidoUseCase;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Integer> criaPedido(@RequestBody @Valid PedidoDTO pedidoRequest) {
        var pedidoDomain = mapper.convertValue(pedidoRequest, Pedido.class);
        var numeroPedido = pedidoUseCase.store(pedidoDomain);

        return ResponseEntity.ok(numeroPedido);
    }
}
