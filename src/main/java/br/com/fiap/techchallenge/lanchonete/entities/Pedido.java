package br.com.fiap.techchallenge.lanchonete.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer numero;
    private String identificacaoCliente;
    private List<Integer> produtoId;
    private EtapaPedido etapaPedido = EtapaPedido.RECEBIDO;
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
}
