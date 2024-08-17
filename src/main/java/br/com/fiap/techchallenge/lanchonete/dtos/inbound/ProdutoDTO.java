package br.com.fiap.techchallenge.lanchonete.dtos.inbound;

import br.com.fiap.techchallenge.lanchonete.entities.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProdutoDTO {

    private Integer id;

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
}
