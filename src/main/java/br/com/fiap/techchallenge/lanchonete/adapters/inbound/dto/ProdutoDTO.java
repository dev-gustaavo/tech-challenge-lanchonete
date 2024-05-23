package br.com.fiap.techchallenge.lanchonete.adapters.inbound.dto;

import br.com.fiap.techchallenge.lanchonete.core.domain.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoDTO {

    @NotNull(message = "Nome é obrigatório.")
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "Categoria do produto é obrigatória.")
    private CategoriaProduto categoria;

    @NotNull(message = "Preço do produto é obrigatório.")
    @Positive(message = "O preço do produto deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "Descrição é obrigatória.")
    @NotBlank(message = "Descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "Defina uma imagem para o produto")
    @NotBlank(message = "Defina uma imagem para o produto")
    private String imagemPath;

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = CategoriaProduto.fromString(categoria);
    }
}
