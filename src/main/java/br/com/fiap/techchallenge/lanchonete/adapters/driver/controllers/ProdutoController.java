package br.com.fiap.techchallenge.lanchonete.adapters.driver.controllers;

import br.com.fiap.techchallenge.lanchonete.adapters.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.core.application.usecases.produto.IProdutoUseCase;
import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final IProdutoUseCase produtoUseCase;
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoRequest) throws Exception {
        var produtoDomain = mapper.convertValue(produtoRequest, Produto.class);

        return ResponseEntity.ok(produtoUseCase.save(produtoDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterarProduto(@RequestBody @Valid ProdutoDTO produtoRequest,
                                                  @PathVariable(value = "id") Integer id) throws Exception {
        var produtoDomain = mapper.convertValue(produtoRequest, Produto.class);

        return ResponseEntity.ok(produtoUseCase.update(id, produtoDomain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable(value = "id") Integer id) throws Exception {
        produtoUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<List<Produto>> buscarProdutoPorCategoria(@PathVariable(value = "categoria") String categoria) throws Exception {
        return ResponseEntity.ok(produtoUseCase.buscaProdutoPorCategoria(categoria));
    }

}
