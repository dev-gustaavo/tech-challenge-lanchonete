package br.com.fiap.techchallenge.lanchonete.dtos.inbound;

import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WebhookDTO {

    @NotNull(message = "O número do pedido é obrigatório.")
    private Integer numeroPedido;

    @NotNull(message = "O status de pagamento é obrigatório.")
    private StatusPagamento statusPagamento;
}
