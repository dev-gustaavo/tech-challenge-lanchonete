package br.com.fiap.techchallenge.lanchonete.adapters.driver.controllers;

import br.com.fiap.techchallenge.lanchonete.adapters.dto.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.core.application.usecases.IClienteUseCase;
import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final IClienteUseCase clienteUseCase;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Validated ClienteDTO clienteRequest) {
        var clienteDomain = mapper.convertValue(clienteRequest, Cliente.class);

        return ResponseEntity.ok(clienteUseCase.save(clienteDomain));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteUseCase.list());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody @Validated ClienteDTO clienteRequest,
                                                    @PathVariable(value = "id") Integer id) {
        var clienteDomain = mapper.convertValue(clienteRequest, Cliente.class);

        return ResponseEntity.ok(clienteUseCase.update(id, clienteDomain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaCliente(@PathVariable(value = "id") Integer id) {
        clienteUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
