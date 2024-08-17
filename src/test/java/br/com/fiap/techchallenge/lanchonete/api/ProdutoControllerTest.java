package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ProdutoMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoDTOMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoMock;
import jakarta.persistence.EntityNotFoundException;
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
public class ProdutoControllerTest {

    @Mock
    private ProdutoUseCase produtoUseCase;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoController produtoController;

    private final Produto produtoMock = ProdutoMock.getProduto();
    private final ProdutoDTO produtoDTOMock = ProdutoDTOMock.getProdutoDto();

    @Test
    @Description("Deve cadastrar um produto")
    void deveCadastrarUmProduto() throws Exception {
        when(produtoMapper.toEntity(any())).thenReturn(produtoMock);
        when(produtoUseCase.save(any())).thenReturn(produtoMock);

        var result = produtoController.cadastrarProduto(produtoDTOMock);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.CREATED.value()),
                () -> assertEquals(body.getNome(), produtoMock.getNome()),
                () -> assertEquals(body.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(body.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(body.getId(), produtoMock.getId()),
                () -> assertEquals(body.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(body.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar uma exception ao tentar cadastrar um produto")
    void deveRetornarUmaExceptionQuandoTentarCadastrarUmProduto() throws Exception {
        when(produtoMapper.toEntity(any())).thenReturn(produtoMock);
        when(produtoUseCase.save(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> produtoController.cadastrarProduto(produtoDTOMock));
    }

    @Test
    @Description("Deve atualizar um produto")
    void deveAtualizarUmProduto() throws Exception {
        when(produtoMapper.toEntity(any())).thenReturn(produtoMock);
        when(produtoUseCase.update(any())).thenReturn(produtoMock);

        var result = produtoController.alterarProduto(produtoDTOMock);
        var body = result.getBody();

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(body.getNome(), produtoMock.getNome()),
                () -> assertEquals(body.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(body.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(body.getId(), produtoMock.getId()),
                () -> assertEquals(body.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(body.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar uma exception quando tentar atualizar um produto")
    void deveRetornarUmaExceptionQuandoTentarAtualizarUmProduto() throws Exception {
        when(produtoMapper.toEntity(any())).thenReturn(produtoMock);
        when(produtoUseCase.update(any())).thenThrow(new Exception("erro"));

        assertThrows(Exception.class, () -> produtoController.alterarProduto(produtoDTOMock));
    }

    @Test
    @Description("Deve deletar um produto")
    void deveDeletarUmProduto() throws Exception {
        doNothing().when(produtoUseCase).delete(anyInt());
        var result = produtoController.deletarProduto(1);
        assertEquals(result.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    @Description("Deve retornar uma exception ao tentar deletar um produto")
    void deveRetornarUmaExceptionAoTentarDeletarUmProduto() throws Exception {
        doThrow(new Exception("erro")).when(produtoUseCase).delete(anyInt());
        assertThrows(Exception.class, () -> produtoController.deletarProduto(1));
    }

    @Test
    @Description("Deve buscar uma lista de produtos por categoria")
    void deveBuscarUmaListaDeProdutosPorCategoria() throws Exception {
        when(produtoUseCase.buscaProdutoPorCategoria(anyString())).thenReturn(List.of(produtoMock));

        var result = produtoController.buscarProdutoPorCategoria("LANCHE");
        var body = result.getBody();
        var item = body.get(0);

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(item.getNome(), produtoMock.getNome()),
                () -> assertEquals(item.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(item.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(item.getId(), produtoMock.getId()),
                () -> assertEquals(item.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(item.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve buscar produtos da categoria LANCHE")
    void deveBuscarProdutosDaCategoriaLanche() throws Exception {
        when(produtoUseCase.buscaProdutoPorCategoria("LANCHE")).thenReturn(List.of(produtoMock));

        var result = produtoController.buscarProdutoPorCategoria("LANCHE");
        var body = result.getBody();
        var item = body.get(0);

        assertAll(
                () -> assertEquals(result.getStatusCode().value(), HttpStatus.OK.value()),
                () -> assertEquals(item.getNome(), produtoMock.getNome()),
                () -> assertEquals(item.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(item.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(item.getId(), produtoMock.getId()),
                () -> assertEquals(item.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(item.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException quando não encontrar a categoria solicitada")
    void deveRetornarEntityNotFoundExceptionQuandonaoEncontrarACateogiraSolicitada() throws Exception {
        when(produtoUseCase.buscaProdutoPorCategoria("BLABLA")).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> produtoController.buscarProdutoPorCategoria("BLABLA"));
    }

    @Test
    @Description("Deve retornar uma exception ao tentar listar produtos por categoria")
    void deveRetornarUmaExceptionAoTentarDeletarCliente() throws Exception {
        when(produtoUseCase.buscaProdutoPorCategoria(anyString())).thenThrow(new Exception("erro"));
        assertThrows(Exception.class, () -> produtoController.buscarProdutoPorCategoria("LANCHE"));
    }
}
