package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.cliente;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.ClienteEntity;
import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ClienteRepository implements IClienteRepository {

    private final RepositoryCliente repositoryCliente;
    private final ObjectMapper mapper;

    @Override
    public Cliente save(Cliente cliente) throws Exception {
        try {
            var clienteEntity = mapper.convertValue(cliente, ClienteEntity.class);

            clienteEntity = repositoryCliente.save(clienteEntity);

            return mapper.convertValue(clienteEntity, Cliente.class);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Cliente> list() {
        var clienteEntities = repositoryCliente.findAll();
        var clientes = clienteEntities.stream()
                .map(clienteEntity -> mapper.convertValue(clienteEntity, Cliente.class))
                .collect(Collectors.toList());
        return clientes;
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) {
        var clienteEntityOptional = repositoryCliente.findById(id);

        if (clienteEntityOptional.isPresent()) {
            return aplicaAlteracoes(cliente, clienteEntityOptional);
        } else {
            throw new EntityNotFoundException("Cliente não encontrado para atualização.");
        }
    }

    @Override
    public void delete(Integer id) {
        repositoryCliente.deleteById(id);
    }

    private Cliente aplicaAlteracoes(Cliente cliente, Optional<ClienteEntity> clienteEntityOptional) {
        var clienteEntity = clienteEntityOptional.get();

        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setCpf(cliente.getCpf());

        var clienteAtualizado = repositoryCliente.save(clienteEntity);
        return mapper.convertValue(clienteAtualizado, Cliente.class);
    }
}

