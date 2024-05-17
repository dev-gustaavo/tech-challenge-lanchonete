package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne(optional = true)
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedidoEntity> itens;

    @Column(nullable = false)
    private String etapaPedido;

}
