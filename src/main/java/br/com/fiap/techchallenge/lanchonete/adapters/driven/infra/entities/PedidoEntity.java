package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities;

import br.com.fiap.techchallenge.lanchonete.core.domain.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;
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

    @Column
    private String identificacaoCliente;

    @Column(nullable = false)
    private String etapaPedido = EtapaPedido.RECEBIDO.toString();

    @Column(nullable = false)
    private String statusPagamento = StatusPagamento.PENDENTE.toString();

    @ElementCollection
    @CollectionTable(name = "produtos_pedido", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Integer> produtoId;
}

