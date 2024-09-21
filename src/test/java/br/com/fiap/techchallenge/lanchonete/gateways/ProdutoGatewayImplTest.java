package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ProdutoMapper;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ProdutoEntity;
import br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection.RepositoryProduto;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoMock;
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
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoGatewayImplTest {

    @Mock
    private RepositoryProduto repositoryProduto;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    private final ProdutoEntity produtoEntityMock = ProdutoEntityMock.getProdutoEntity();
    private final Produto produtoMock = ProdutoMock.getProduto();

    @Test
    @Description("Deve salvar um produto no banco de dados")
    void deveSalvarProdutoBancoDados() throws Exception {
        when(produtoMapper.toDbEntity(any())).thenReturn(produtoEntityMock);
        when(repositoryProduto.save(any())).thenReturn(produtoEntityMock);
        when(produtoMapper.fromDbEntityToEntity(any())).thenReturn(produtoMock);

        var result = produtoGateway.save(produtoMock);

        verify(produtoMapper, times(1)).toDbEntity(any());
        verify(repositoryProduto, times(1)).save(any());
        verify(produtoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao())
        );
    }

    @Test
    @Description("Deve retornar Exception ao tentar salvar um produto")
    void deveRetornarExceptionAoTentarSalvarProduto() {
        when(produtoMapper.toDbEntity(any())).thenReturn(produtoEntityMock);
        when(repositoryProduto.save(any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> produtoGateway.save(produtoMock));
        verify(produtoMapper, times(1)).toDbEntity(any());
        verify(repositoryProduto, times(1)).save(any());
        verify(produtoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve atualizar um produto")
    void deveAtualizarProduto() throws Exception {
        when(repositoryProduto.findById(anyInt())).thenReturn(Optional.of(produtoEntityMock));
        when(produtoMapper.toProdutoEntityAtualizado(any())).thenReturn(produtoEntityMock);
        when(repositoryProduto.save(any())).thenReturn(produtoEntityMock);
        when(produtoMapper.fromDbEntityToEntity(any())).thenReturn(produtoMock);

        var result = produtoGateway.update(produtoMock);

        verify(repositoryProduto, times(1)).findById(anyInt());
        verify(produtoMapper, times(1)).toProdutoEntityAtualizado(any());
        verify(repositoryProduto, times(1)).save(any());
        verify(produtoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException ao tentar atualizar produto")
    void deveRetornarEntityNotFoundExceptionAtualizarProduto() {
        when(repositoryProduto.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoGateway.update(produtoMock));
        verify(repositoryProduto, times(1)).findById(anyInt());
        verify(produtoMapper, times(0)).toProdutoEntityAtualizado(any());
        verify(repositoryProduto, times(0)).save(any());
        verify(produtoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar atualizar produto")
    void deveRetornarExceptionAtualizarProduto() {
        when(repositoryProduto.findById(anyInt())).thenReturn(Optional.of(produtoEntityMock));
        when(produtoMapper.toProdutoEntityAtualizado(any())).thenReturn(produtoEntityMock);
        when(repositoryProduto.save(any())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> produtoGateway.update(produtoMock));
        verify(repositoryProduto, times(1)).findById(anyInt());
        verify(produtoMapper, times(1)).toProdutoEntityAtualizado(any());
        verify(repositoryProduto, times(1)).save(any());
        verify(produtoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve deletar um produto")
    void deveDeletarProduto() throws Exception {
        produtoGateway.delete(1);
        verify(repositoryProduto, times(1)).deleteById(anyInt());
    }

    @Test
    @Description("Deve retornar Exception ao tentar deletar um produto")
    void deveRetornarExceptionDeletarProduto() {
        doThrow(new RuntimeException()).when(repositoryProduto).deleteById(anyInt());
        assertThrows(Exception.class, () -> produtoGateway.delete(1));
    }

    @Test
    @Description("Deve retornar uma lista de produtos por categoria")
    void deveRetornarListaProdutoCategoria() throws Exception {
        when(repositoryProduto.findByCategoria(anyString())).thenReturn(Optional.of(List.of(produtoEntityMock)));
        when(produtoMapper.fromDbEntityToEntity(any())).thenReturn(produtoMock);

        var result = produtoGateway.buscaPorCategoria("categoria");

        verify(repositoryProduto, times(1)).findByCategoria(anyString());
        verify(produtoMapper, times(1)).fromDbEntityToEntity(any());
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.get(0).getNome(), produtoMock.getNome()),
                () -> assertEquals(result.get(0).getImagemPath(), produtoMock.getImagemPath()),
                () -> assertEquals(result.get(0).getCategoria(), produtoMock.getCategoria()),
                () -> assertEquals(result.get(0).getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.get(0).getId(), produtoMock.getId()),
                () -> assertEquals(result.get(0).getDescricao(), produtoMock.getDescricao())
        );
    }

    @Test
    @Description("Deve retornar EntityNotFoundException ao tentar buscar um produto por categoria")
    void deveRetornarEntityNotFoundExceptionBuscarProdutoCategoria() {
        when(repositoryProduto.findByCategoria(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoGateway.buscaPorCategoria("nao encontrado"));
        verify(repositoryProduto, times(1)).findByCategoria(anyString());
        verify(produtoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve retornar Exception ao tentar buscar um produto por categoria")
    void deveRetornarExceptionBuscarProdutoCategoria() {
        when(repositoryProduto.findByCategoria(anyString())).thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> produtoGateway.buscaPorCategoria("nao encontrado"));
        verify(repositoryProduto, times(1)).findByCategoria(anyString());
        verify(produtoMapper, times(0)).fromDbEntityToEntity(any());
    }

    @Test
    @Description("Deve verificar se é um produto")
    void deveVerificarProduto() {
        when(repositoryProduto.findById(anyInt())).thenReturn(Optional.of(produtoEntityMock));
        assertTrue(produtoGateway.isProduto(1));
    }

    @Test
    @Description("Deve verificar que não é um produto")
    void deveVerificarQueNaoEhProduto() {
        when(repositoryProduto.findById(anyInt())).thenReturn(Optional.empty());
        assertFalse(produtoGateway.isProduto(1));
    }
}
