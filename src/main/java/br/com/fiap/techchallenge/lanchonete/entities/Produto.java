package br.com.fiap.techchallenge.lanchonete.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Integer id;
    private String nome;
    private CategoriaProduto categoria;
    private BigDecimal preco;
    private String descricao;
    private String imagemPath;
}
