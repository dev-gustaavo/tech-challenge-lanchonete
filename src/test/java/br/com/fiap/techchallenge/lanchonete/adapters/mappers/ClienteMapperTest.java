package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Cliente;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteDTOMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ClienteMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteMapperTest {

    private ClienteMapper clienteMapper = ClienteMapper.INSTANCE;
    private final ClienteDTO clienteDTOMock = ClienteDTOMock.getClienteDTO();
    private final Cliente clienteMock = ClienteMock.getCliente();
    private final ClienteEntity clienteEntityMock = ClienteEntityMock.getClienteEntity();

    @Test
    @Description("Deve retornar null o DTO for null")
    void deveRetornarNullQuandoDtoNull() {
        assertNull(clienteMapper.toEntity(null));
    }

    @Test
    @Description("Deve retornar o objeto cliente")
    void deveRetornarObjetoCliente() {
        var result = clienteMapper.toEntity(clienteDTOMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteDTOMock.getNome()),
                () -> assertEquals(result.getEmail(), clienteDTOMock.getEmail()),
                () -> assertEquals(result.getCpf(), clienteDTOMock.getCpf())
        );
    }

    @Test
    @Description("Deve retornar null quando Cliente for null")
    void deveRetornarNullQuandoClienteForNull() {
        assertNull(clienteMapper.toDbEntity(null));
    }

    @Test
    @Description("Deve retornar Cliente Entity")
    void deveRetornarClienteEntity() {
        var result = clienteMapper.toDbEntity(clienteMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteMock.getNome()),
                () -> assertEquals(result.getEmail(), clienteMock.getEmail()),
                () -> assertEquals(result.getCpf(), clienteMock.getCpf())
        );
    }

    @Test
    @Description("Deve retornar null quando Cliente Entity for null")
    void deveRetornarNullQuandoClienteEntityNull() {
        assertNull(clienteMapper.fromDbEntityToEntity(null));
    }

    @Test
    @Description("Deve retornar Cliente a partir de Cliente Entity")
    void deveRetornarClienteApartirDeClienteEntity() {
        var result = clienteMapper.fromDbEntityToEntity(clienteEntityMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), clienteEntityMock.getNome()),
                () -> assertEquals(result.getEmail(), clienteEntityMock.getEmail()),
                () -> assertEquals(result.getCpf(), clienteEntityMock.getCpf())
        );
    }
}
