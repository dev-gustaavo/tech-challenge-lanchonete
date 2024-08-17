package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ClienteMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ClienteUseCase;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteDTOMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Mock
    private ClienteUseCase clienteUseCase;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteController clienteController;

    private final Cliente clienteMock = ClienteMock.getCliente();
    private final ClienteDTO clienteDtomock = ClienteDTOMock.getClienteDTO();

    @Test
    @Description("Deve criar um cliente com sucesso")
    void criarClienteTest() throws Exception {
        when(clienteMapper.toEntity(any())).thenReturn(clienteMock);
        when(clienteUseCase.save(any())).thenReturn(clienteMock);

        var result = clienteController.criarCliente(clienteDtomock);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(body.getNome(), clienteMock.getNome()),
                () -> assertEquals(body.getEmail(), clienteMock.getEmail()),
                () -> assertEquals(body.getCpf(), clienteMock.getCpf())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar criar um cliente")
    void deveRetornarUmaExceptionQuandoTentarCriarUmCliente() throws Exception {
        when(clienteMapper.toEntity(any())).thenReturn(clienteMock);
        when(clienteUseCase.save(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> clienteController.criarCliente(clienteDtomock));
    }

    @Test
    @Description("Deve buscar um cliente a partir de um CPF")
    void buscarClientePorCpfTest() throws Exception {
        when(clienteUseCase.buscarClientePorCpf(anyString())).thenReturn(clienteMock);

        var result = clienteController.buscarClientePorCpf("01234567890");
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(body.getNome(), clienteMock.getNome()),
                () -> assertEquals(body.getEmail(), clienteMock.getEmail()),
                () -> assertEquals(body.getCpf(), clienteMock.getCpf())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar buscar um cliente pelo CPF")
    void deveRetornarUmaExceptionQuandoTentarBuscarClientePorCPF() throws Exception {
        when(clienteUseCase.buscarClientePorCpf(anyString())).thenThrow(new Exception());
        assertThrows(Exception.class, () -> clienteController.buscarClientePorCpf("01234567890"));
    }

    @Test
    @Description("Deve listar todos os clientes")
    void listarClientesTest() throws Exception {
        when(clienteUseCase.list()).thenReturn(List.of(clienteMock));

        var result = clienteController.listarClientes();
        var body = result.getBody();
        var item = body.get(0);

        assertAll(
                () -> assertEquals(body.size(), 1),
                () -> assertEquals(item.getNome(), clienteMock.getNome()),
                () -> assertEquals(item.getCpf(), clienteMock.getCpf()),
                () -> assertEquals(item.getEmail(), clienteMock.getEmail())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar listar todos os clientes")
    void deveRetornarExceptionQuandoTentarListarClientes() throws Exception {
        when(clienteUseCase.list()).thenThrow(new Exception("erro"));
        assertThrows(Exception.class, () -> clienteController.listarClientes());
    }

    @Test
    @Description("Deve atualizar um cliente")
    void atualizarClienteTest() throws Exception {
        when(clienteMapper.toEntity(any())).thenReturn(clienteMock);
        when(clienteUseCase.update(any(), any())).thenReturn(clienteMock);

        var result = clienteController.atualizarCliente(clienteDtomock, 1);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(body.getNome(), clienteMock.getNome()),
                () -> assertEquals(body.getEmail(), clienteMock.getEmail()),
                () -> assertEquals(body.getCpf(), clienteMock.getCpf())
        );
    }

    @Test
    @Description("Deve retornar uma exception ao tentar atualizar um cliente")
    void deveRetornarUmaExceptionAoTentarAtualizarCliente() throws Exception {
        when(clienteMapper.toEntity(any())).thenReturn(clienteMock);
        when(clienteUseCase.update(any(), any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> clienteController.atualizarCliente(clienteDtomock, 1));
    }

    @Test
    @Description("Deve deletar um cliente com sucesso")
    void deletarClienteTest() throws Exception {
        doNothing().when(clienteUseCase).delete(any());
        var result = clienteController.deletaCliente(1);
        assertEquals(result.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    @Description("Deve retornar uma exception ao tentar deletar um cliente")
    void deveRetornarUmaExceptionAoTentarDeletarCliente() throws Exception {
        doThrow(new Exception("erro")).when(clienteUseCase).delete(any());
        assertThrows(Exception.class, () -> clienteController.deletaCliente(1));
    }
}
