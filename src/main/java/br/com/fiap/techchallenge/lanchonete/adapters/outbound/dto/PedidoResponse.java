package br.com.fiap.techchallenge.lanchonete.adapters.outbound.dto;

import br.com.fiap.techchallenge.lanchonete.core.domain.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {
    private Integer codigo;
    private String mensagem;
    private String statusPagamento;
    private String etapaPedido;
}
