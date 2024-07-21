package br.com.fiap.techchallenge.lanchonete.dtos.outbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {
    private Integer codigo;
    private String statusPagamento;
    private String etapaPedido;
}
