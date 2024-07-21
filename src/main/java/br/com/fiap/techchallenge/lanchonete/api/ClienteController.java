package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ClienteMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid ClienteDTO clienteRequest) throws Exception {
        var clienteEntity = clienteMapper.toEntity(clienteRequest);

        var novoCliente = clienteUseCase.save(clienteEntity);

        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable(value = "cpf") String cpf) throws Exception {
        var cliente = clienteUseCase.buscarClientePorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() throws Exception {
        var clientes = clienteUseCase.list();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody @Validated ClienteDTO clienteRequest,
                                                    @PathVariable(value = "id") Integer id) throws Exception {
        var clienteDomain = clienteMapper.toEntity(clienteRequest);
        var clienteAtualizado = clienteUseCase.update(id, clienteDomain);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaCliente(@PathVariable(value = "id") Integer id) throws Exception {
        clienteUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}

