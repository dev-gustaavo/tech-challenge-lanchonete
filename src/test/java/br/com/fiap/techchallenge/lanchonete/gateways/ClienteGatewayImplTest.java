package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ClienteMapper;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;
import br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection.RepositoryCliente;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteMock;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteGatewayImplTest {

    @Mock
    private RepositoryCliente repositoryCliente;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteGatewayImpl clienteGateway;

    private final ClienteEntity clienteEntityMock = ClienteEntityMock.getClienteEntity();
    private final Cliente clienteMock = ClienteMock.getCliente();

    @Test
    @Description("Deve salvar um cliente no banco de dados")
    void deveSalvarClienteBancoDados() throws Exception {
        when(clienteMapper.toDbEntity(any())).thenReturn(clienteEntityMock);
        when(repositoryCliente.save(any())).thenReturn(clienteEntityMock);
        when(clienteMapper.fromDbEntityToEntity(any())).thenReturn(clienteMock);

        var result = clienteGateway.save(clienteMock);

        verify(repositoryCliente, times(1)).save(any());
        verify(clienteMapper, times(1)).toDbEntity(any());
        verify(clienteMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar exception ao tentar salvar um cliente no banco de dados")
    void deveRetornarExceptionAoTentarSalvarUmClienteNoBancoDeDados() throws Exception {
        when(clienteMapper.toDbEntity(any())).thenReturn(clienteEntityMock);
        when(repositoryCliente.save(any())).thenThrow(new RuntimeException("erro"));

        assertThrows(Exception.class, () -> clienteGateway.save(clienteMock));

        verify(repositoryCliente, times(1)).save(any());
        verify(clienteMapper, times(1)).toDbEntity(any());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());

    }

    @Test
    @Description("Deve buscar um cliente por CPF")
    void deveBuscarClientePorCpf() throws Exception {
        when(repositoryCliente.findByCpf(anyString())).thenReturn(Optional.ofNullable(clienteEntityMock));
        when(clienteMapper.fromDbEntityToEntity(any())).thenReturn(clienteMock);

        var result = clienteGateway.buscarClientePorCpf("01234567890");

        verify(repositoryCliente, times(1)).findByCpf(anyString());
        verify(clienteMapper, times(1)).fromDbEntityToEntity(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException quando não for encontrado um cliente com o CPF informado")
    void deveRetornarEntityNotFoundExceptionQuandoNaoEncontrarClienteComCpfInformado() throws Exception {
        when(repositoryCliente.findByCpf(anyString())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> clienteGateway.buscarClientePorCpf("01234567890"));
        verify(repositoryCliente, times(1)).findByCpf(anyString());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar buscar um cliente com o CPF")
    void deveRetornarExceptionAoTentarBuscarClienteComCpf() throws Exception {
        when(repositoryCliente.findByCpf(anyString())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> clienteGateway.buscarClientePorCpf("01234567890"));

        verify(repositoryCliente, times(1)).findByCpf(anyString());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar uma lista de clientes")
    void deveRetornarUmaListaDeClientes() throws Exception {
        when(repositoryCliente.findAll()).thenReturn(List.of(clienteEntityMock));
        when(clienteMapper.fromDbEntityToEntity(any())).thenReturn(clienteMock);

        var result = clienteGateway.list();
        var item = result.get(0);

        verify(repositoryCliente, times(1)).findAll();
        verify(clienteMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.size(), 1),
                () -> assertEquals(item.getNome(), clienteMock.getNome()),
                () -> assertEquals(item.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(item.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException quando não houver nenhum cliente na lista")
    void deveRetornarEntityNotFoundExceptionQuandonaoHouverClienteNaLista() throws Exception {
        when(repositoryCliente.findAll()).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> clienteGateway.list());

        verify(repositoryCliente, times(1)).findAll();
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar buscar a lista de clientes")
    void deveRetornarExceptionAoTentarBuscarListaClientes() throws Exception {
        when(repositoryCliente.findAll()).thenThrow(new RuntimeException("erro"));

        assertThrows(Exception.class, () -> clienteGateway.list());
        verify(repositoryCliente, times(1)).findAll();
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve alterar um cliente no banco de dados")
    void deveAlterarClienteBancoDados() throws Exception {
        when(repositoryCliente.findById(anyInt())).thenReturn(Optional.ofNullable(clienteEntityMock));
        when(repositoryCliente.save(any())).thenReturn(clienteEntityMock);
        when(clienteMapper.fromDbEntityToEntity(any())).thenReturn(clienteMock);
        when(clienteMapper.toDbEntity(any())).thenReturn(clienteEntityMock);

        var result = clienteGateway.updateCliente(1, clienteMock);

        verify(repositoryCliente, times(1)).findById(any());
        verify(repositoryCliente, times(1)).save(any());
        verify(clienteMapper, times(1)).fromDbEntityToEntity(any());
        verify(clienteMapper, times(1)).toDbEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException ao tentar alterar um cliente no banco de dados")
    void deveRetornarEntityNotFoundExceptionAoTentarAlterarClienteBancoDados() throws Exception {
        when(repositoryCliente.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteGateway.updateCliente(1, clienteMock));

        verify(repositoryCliente, times(1)).findById(any());
        verify(repositoryCliente, times(0)).save(any());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
        verify(clienteMapper, times(0)).toDbEntity(any());
    }

    @Test
    @Description("Deve retornar exception ao tentar alterar um cliente no banco de dados")
    void deveRetornarExceptionAoTentarAlterarUmClienteNoBancoDeDados() {
        when(repositoryCliente.findById(anyInt())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> clienteGateway.updateCliente(1, clienteMock));

        verify(repositoryCliente, times(1)).findById(any());
        verify(repositoryCliente, times(0)).save(any());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
        verify(clienteMapper, times(0)).toDbEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar deletar um cliente do banco de dados")
    void deveRetornarExceptionAoTentarDeletarUmClienteDoBancoDeDados() {
        doThrow(new RuntimeException()).when(repositoryCliente).deleteById(anyInt());

        assertThrows(Exception.class, () -> clienteGateway.delete(1));
    }

    @Test
    @Description("Deve deletar um cliente")
    void deveDeletarUmCliente() throws Exception {
        clienteGateway.delete(1);
        verify(repositoryCliente, times(1)).deleteById(anyInt());
    }

    @Test
    @Description("Deve retornar EntityNotFoundException ao buscar um cliente pelo CPF com optional")
    void deveRetornarEntityNotFoundExceptionAoBuscarClientePorCpfComOptional() throws Exception {
        when(repositoryCliente.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteGateway.buscarClientePorCpf("01234567890"));
        verify(repositoryCliente, times(1)).findByCpf(anyString());
        verify(clienteMapper, times(0)).fromDbEntityToEntity(any());
    }
}
