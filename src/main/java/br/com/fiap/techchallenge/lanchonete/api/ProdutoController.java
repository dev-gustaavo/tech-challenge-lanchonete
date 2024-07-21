package br.com.fiap.techchallenge.lanchonete.api;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.ProdutoMapper;
import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.lanchonete.entities.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;
    private final ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoRequest) throws Exception {
        var produtoDomain = produtoMapper.toEntity(produtoRequest);

        var produto = produtoUseCase.save(produtoDomain);

        return ResponseEntity.
                status(HttpStatus.CREATED.value())
                .body(produto);
    }

    @PutMapping
    public ResponseEntity<Produto> alterarProduto(@RequestBody @Valid ProdutoDTO produtoRequest) throws Exception {
        var produtoDomain = produtoMapper.toEntity(produtoRequest);

        var produto = produtoUseCase.update(produtoDomain);

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable(value = "id") Integer id) throws Exception {
        produtoUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<List<Produto>> buscarProdutoPorCategoria(@PathVariable(value = "categoria") String categoria) throws Exception {
        var produtos = produtoUseCase.buscaProdutoPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

}
