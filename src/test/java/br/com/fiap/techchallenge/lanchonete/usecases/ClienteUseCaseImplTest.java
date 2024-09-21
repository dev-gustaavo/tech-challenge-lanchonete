package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ClienteGateway;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteMock;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ClienteUseCaseImpl clienteUseCase;

    private Cliente clienteMock = ClienteMock.getCliente();

    @Test
    @Description("Deve salvar um cliente")
    void deveSalvarCliente() throws Exception {
        when(clienteGateway.buscarClientePorCpf(anyString())).thenThrow(new EntityNotFoundException());
        when(clienteGateway.save(any())).thenReturn(clienteMock);

        var result = clienteUseCase.save(clienteMock);

        verify(clienteGateway, times(1)).buscarClientePorCpf(anyString());
        verify(clienteGateway, times(1)).save(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar erro de cliente ja cadastrado")
    void deveRetornarErroClienteCadastrado() throws Exception {
        when(clienteGateway.buscarClientePorCpf(anyString())).thenReturn(clienteMock);

        assertThrows(Exception.class, () -> clienteUseCase.save(clienteMock));
        verify(clienteGateway, times(1)).buscarClientePorCpf(anyString());
        verify(clienteGateway, times(0)).save(any());
    }

    @Test
    @Description("Deve retornar exception genérica ao tentar salvar cliente")
    void deveRetornarExceptionGenéricaAoTentarSalvarCliente() throws Exception {
        when(clienteGateway.buscarClientePorCpf(anyString())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> clienteUseCase.save(clienteMock));
        verify(clienteGateway, times(1)).buscarClientePorCpf(anyString());
        verify(clienteGateway, times(0)).save(any());
    }

    @Test
    @Description("Deve retornar Cliente por CPF")
    void deveRetornarClientePorCpf() throws Exception {
        when(clienteGateway.buscarClientePorCpf(anyString())).thenReturn(clienteMock);

        var result = clienteUseCase.buscarClientePorCpf("01234567890");

        verify(clienteGateway, times(1)).buscarClientePorCpf(anyString());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar buscar cliente por CPF")
    void deveRetornarExceptionAoTentarBuscarClientePorCpf() throws Exception {
        when(clienteGateway.buscarClientePorCpf(anyString())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> clienteUseCase.buscarClientePorCpf("01234567890"));
    }

    @Test
    @Description("Deve retornar uma lista de clientes")
    void deveRetornarUmaListaDeClientes() throws Exception {
        when(clienteGateway.list()).thenReturn(List.of(clienteMock));

        var result = clienteUseCase.list();

        verify(clienteGateway, times(1)).list();
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.get(0).getNome(), clienteMock.getNome()),
                () -> assertEquals(result.get(0).getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.get(0).getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar uma Exception ao tentar listar clientes")
    void deveRetornarExceptionAoTentarListarClientes() throws Exception {
        when(clienteGateway.list()).thenThrow(new Exception());
        assertThrows(Exception.class, () -> clienteUseCase.list());
    }

    @Test
    @Description("Deve fazer update no cliente")
    void deveFazerUpdateNoCliente() throws Exception {
        when(clienteGateway.updateCliente(anyInt(), any())).thenReturn(clienteMock);

        var result = clienteUseCase.update(1, clienteMock);

        verify(clienteGateway, times(1)).updateCliente(anyInt(), any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar fazer update no cliente")
    void deveRetornarExceptionAoTentarFazerUpdateNoCliente() throws Exception {
        when(clienteGateway.updateCliente(anyInt(), any())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> clienteUseCase.update(1, clienteMock));
    }

    @Test
    @Description("Deve deletar um cliente")
    void deveDeletarUmCliente() throws Exception {
        clienteUseCase.delete(1);
        verify(clienteGateway, times(1)).delete(anyInt());
    }

    @Test
    @Description("Deve retornar Exception ao tentar deletar um cliente")
    void deveRetornarExceptionAoTentarDeletarUmCliente() throws Exception {
        doThrow(new Exception()).when(clienteGateway).delete(anyInt());

        assertThrows(Exception.class, () -> clienteUseCase.delete(1));
    }
}
