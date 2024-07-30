package br.com.fiap.techchallenge.lanchonete.entities.dbEntities;

import br.com.fiap.techchallenge.lanchonete.entities.EtapaPedido;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

