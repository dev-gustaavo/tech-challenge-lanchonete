package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ProdutoEntity;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoDTOMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoEntityMock;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoMapperTest {

    private ProdutoMapper produtoMapper = ProdutoMapper.INSTANCE;
    private final Produto produtoMock = ProdutoMock.getProduto();
    private final Produto produtoCategoriaNullMock = ProdutoMock.getProdutoCategoriaNull();
    private final ProdutoEntity produtoEntityMock = ProdutoEntityMock.getProdutoEntity();
    private final ProdutoEntity produtoEntityCategoriaNullMock = ProdutoEntityMock.getProdutoEntityCategoriaNull();
    private final ProdutoDTO produtoDTOMock = ProdutoDTOMock.getProdutoDto();

    @Test
    @Description("Deve retornar null o DTO for null")
    void deveRetornarNullQuandoDtoNull() {
        assertNull(produtoMapper.toEntity(null));
    }

    @Test
    @Description("Deve retornar o objeto Produto")
    void deveRetornarObjetoPedido() {
        var result = produtoMapper.toEntity(produtoDTOMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoDTOMock.getId()),
                () -> assertEquals(result.getNome(), produtoDTOMock.getNome()),
                () -> assertEquals(result.getCategoria(), produtoDTOMock.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoDTOMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoDTOMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoDTOMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar null quando Produto for null")
    void deveRetornarNullQuandoProdutoForNull() {
        assertNull(produtoMapper.toDbEntity(null));
    }

    @Test
    @Description("Deve retornar objeto Produto Entity")
    void deveRetornarObjetoProdutoEntity() {
        var result = produtoMapper.toDbEntity(produtoMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria().name()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar objeto Produto Entity com Categoria null")
    void deveRetornarObjetoProdutoEntityComCategoriaNull() {
        var result = produtoMapper.toDbEntity(produtoCategoriaNullMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoCategoriaNullMock.getId()),
                () -> assertEquals(result.getNome(), produtoCategoriaNullMock.getNome()),
                () -> assertNull(result.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoCategoriaNullMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoCategoriaNullMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoCategoriaNullMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar null quando Produto Entity for null")
    void deveRetornarNullQuandoProdutoEntityForNull() {
        assertNull(produtoMapper.fromDbEntityToEntity(null));
    }

    @Test
    @Description("Deve retornar o objeto Produto a partir de Produto Entity")
    void deveRetornarObjetoProdutoApartirDeProdutoEntity() {
        var result = produtoMapper.fromDbEntityToEntity(produtoEntityMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoEntityMock.getId()),
                () -> assertEquals(result.getNome(), produtoEntityMock.getNome()),
                () -> assertEquals(result.getCategoria().name(), produtoEntityMock.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoEntityMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoEntityMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoEntityMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar o objeto Produto com Categoria null")
    void deveRetornarObjetoProdutoComCategoriaNull() {
        var result = produtoMapper.fromDbEntityToEntity(produtoEntityCategoriaNullMock);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoEntityCategoriaNullMock.getId()),
                () -> assertEquals(result.getNome(), produtoEntityCategoriaNullMock.getNome()),
                () -> assertNull(result.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoEntityCategoriaNullMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoEntityCategoriaNullMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoEntityCategoriaNullMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar null quando Produto for null")
    void deveRetornarNullQuandoProdutoNull() {
        assertNull(produtoMapper.toProdutoEntityAtualizado(null));
    }

    @Test
    @Description("Deve retornar ProdutoEntity quando Produto for preenchido")
    void deveRetornarProdutoEntityQuandoProdutoPreenchido() {
        var result = produtoMapper.toProdutoEntityAtualizado(produtoMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoMock.getId()),
                () -> assertEquals(result.getNome(), produtoMock.getNome()),
                () -> assertEquals(result.getCategoria(), produtoMock.getCategoria().toString()),
                () -> assertEquals(result.getPreco(), produtoMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoMock.getImagemPath())
        );
    }

    @Test
    @Description("Deve retornar ProdutoEntity com categoria null")
    void deveRetornarProdutoEntityCategoriaNull() {
        var result = produtoMapper.toProdutoEntityAtualizado(produtoCategoriaNullMock);
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(result.getId(), produtoCategoriaNullMock.getId()),
                () -> assertEquals(result.getNome(), produtoCategoriaNullMock.getNome()),
                () -> assertNull(result.getCategoria()),
                () -> assertEquals(result.getPreco(), produtoCategoriaNullMock.getPreco()),
                () -> assertEquals(result.getDescricao(), produtoCategoriaNullMock.getDescricao()),
                () -> assertEquals(result.getImagemPath(), produtoCategoriaNullMock.getImagemPath())
        );
    }
}
