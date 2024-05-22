package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private PedidoEntity pedido;

    @ElementCollection
    @CollectionTable(name = "produtos_pedido", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Integer> produtoId;
}
