package br.com.fiap.techchallenge.lanchonete.dtos.inbound;

import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebhookDTO {

    @NotNull(message = "O número do pedido é obrigatório.")
    private Integer numeroPedido;

    @NotNull(message = "O status de pagamento é obrigatório.")
    private StatusPagamento statusPagamento;
}
