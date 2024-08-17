package br.com.fiap.techchallenge.lanchonete.dtos.outbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Integer codigo;
    private String statusPagamento;
    private String etapaPedido;
}
