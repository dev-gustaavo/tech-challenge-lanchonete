package br.com.fiap.techchallenge.lanchonete.core.application.usecases;

import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;
import br.com.fiap.techchallenge.lanchonete.core.domain.repositories.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteUseCase implements IClienteUseCase {

    private final IClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) throws Exception {

        try {
            clienteRepository.buscarClientePorCpf(cliente.getCpf());

            throw new Exception("Cliente já cadastrado.");
        } catch (EntityNotFoundException entityNotFoundException) {
            return clienteRepository.save(cliente);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) throws Exception {
        return clienteRepository.buscarClientePorCpf(cpf);
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
