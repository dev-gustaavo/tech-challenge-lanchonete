package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCase;

    private final Produto produtoMock = ProdutoMock.getProduto();
    private final Produto produtoSemIdMock = ProdutoMock.getProdutoSemId();

    @Test
    @Description("Deve cadastrar um produto")
    void deveCadastrarUmProduto() throws Exception {
        when(produtoGateway.save(any())).thenReturn(produtoMock);

        var result = produtoUseCase.save(produtoMock);

        verify(produtoGateway, times(1)).save(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar cadastrar um produto")
    void deveRetornarExceptionAoTentarCadastrarProduto() throws Exception {
        when(produtoGateway.save(any())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> produtoUseCase.save(produtoMock));
        verify(produtoGateway, times(1)).save(any());
    }

    @Test
    @Description("Deve alterar um produto")
    void deveAlterarProduto() throws Exception {
        when(produtoGateway.update(any())).thenReturn(produtoMock);

        var result = produtoUseCase.update(produtoMock);

        verify(produtoGateway, times(1)).update(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar ProdutoException ao tentar cadastrar um produto sem ID")
    void deveRetornarProdutoExceptionAoTentarCadastarProdutoSemId() throws Exception {
        assertThrows(ProdutoException.class, () -> produtoUseCase.update(produtoSemIdMock));
        verify(produtoGateway, times(0)).update(any());
    }

    @Test
    @Description("Deve deletar um produto")
    void deveDeletarProduto() throws Exception {
        doNothing().when(produtoGateway).delete(anyInt());
        produtoUseCase.delete(1);
        verify(produtoGateway, times(1)).delete(anyInt());
    }

    @Test
    @Description("Deve retornar Exception ao tentar deletar um produto")
    void deveRetornarExceptionAoTentarDeletarProduto() throws Exception {
        doThrow(new Exception()).when(produtoGateway).delete(anyInt());
        assertThrows(Exception.class, () -> produtoUseCase.delete(1));
        verify(produtoGateway, times(1)).delete(anyInt());
    }

    @Test
    @Description("Deve buscar um produto por categoria")
    void deveBuscarProdutoPorCategoria() throws Exception {
        when(produtoGateway.buscaPorCategoria(anyString())).thenReturn(List.of(produtoMock));

        var result = produtoUseCase.buscaProdutoPorCategoria("LANCHE");
        var item = result.get(0);

        verify(produtoGateway, times(1)).buscaPorCategoria(anyString());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.size(), 1),
                () -> assertEquals(item.getNome(), produtoMock.getNome()),
                () -> assertEquals(item.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(item.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(item.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(item.getId(), produtoMock.getId()),
                () -> assertEquals(item.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar buscar produto por categoria")
    void deveRetornarExceptionAoTentarBuscarProdutoPorCategoria() throws Exception {
        when(produtoGateway.buscaPorCategoria(anyString())).thenThrow(new Exception());

        assertThrows(Exception.class, () -> produtoUseCase.buscaProdutoPorCategoria("LANCHE"));
        verify(produtoGateway, times(1)).buscaPorCategoria(anyString());
    }
}
