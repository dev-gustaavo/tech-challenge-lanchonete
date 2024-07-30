package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ClienteMapper;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;
import br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection.RepositoryCliente;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ClienteGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ClienteGatewayImpl implements ClienteGateway {

    private final RepositoryCliente repositoryCliente;
    private final ClienteMapper clienteMapper;

    @Override
    public Cliente save(Cliente cliente) throws Exception {
        try {
            var clienteDbEntity = clienteMapper.toDbEntity(cliente);

            clienteDbEntity = repositoryCliente.save(clienteDbEntity);

            return clienteMapper.fromDbEntityToEntity(clienteDbEntity);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) throws Exception {
        try {
            var clienteEntityOptional = repositoryCliente.findByCpf(cpf);

            if (clienteEntityOptional.isPresent())
                return clienteMapper.fromDbEntityToEntity(clienteEntityOptional.get());

            throw new EntityNotFoundException(String.format(MensagemErroPadrao.CLIENTE_NAO_ENCONTRADO_CPF, cpf));

        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(entityNotFoundException.getLocalizedMessage(), entityNotFoundException);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Cliente> list() throws Exception {
        try {
            var clienteEntities = repositoryCliente.findAll();

            return clienteEntities.stream()
                    .map(clienteMapper::fromDbEntityToEntity)
                    .collect(Collectors.toList());

        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(MensagemErroPadrao.LISTA_CLIENTE_VAZIA);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) throws Exception {

        try {
            var clienteEntityOptional = repositoryCliente.findById(id);
            var clienteEntity = aplicaAlteracoes(cliente, clienteEntityOptional);
            return clienteMapper.fromDbEntityToEntity(clienteEntity);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException(MensagemErroPadrao.CLIENTE_NAO_ENCONTRADO_ATUALIZACAO);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            repositoryCliente.deleteById(id);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    private ClienteEntity aplicaAlteracoes(Cliente cliente, Optional<ClienteEntity> clienteEntityOptional) {
        var clienteEntity = clienteEntityOptional.get();

        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setCpf(cliente.getCpf());

        return repositoryCliente.save(clienteEntity);
    }
}

