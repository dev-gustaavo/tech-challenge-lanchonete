package br.com.fiap.techchallenge.lanchonete.core.application.usecases;

import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteUseCase implements IClienteUseCase {

    private final IClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> list() {
        return clienteRepository.list();
    }

    @Override
    public Cliente update(Integer id, Cliente cliente) {
        return clienteRepository.updateCliente(id, cliente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepository.delete(id);
    }
}
