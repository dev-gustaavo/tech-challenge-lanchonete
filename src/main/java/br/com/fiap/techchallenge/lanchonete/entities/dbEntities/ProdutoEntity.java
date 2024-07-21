package br.com.fiap.techchallenge.lanchonete.entities.dbEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "categoria"})
})
@Getter
@Setter
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String imagemPath;
}
