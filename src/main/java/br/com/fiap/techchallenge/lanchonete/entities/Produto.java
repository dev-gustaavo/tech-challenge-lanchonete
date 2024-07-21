package br.com.fiap.techchallenge.lanchonete.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
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
