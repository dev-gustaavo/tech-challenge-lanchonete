package br.com.fiap.techchallenge.lanchonete.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer numero;
    private String identificacaoCliente;
    private List<Integer> produtoId;
    private EtapaPedido etapaPedido = EtapaPedido.RECEBIDO;
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
}
