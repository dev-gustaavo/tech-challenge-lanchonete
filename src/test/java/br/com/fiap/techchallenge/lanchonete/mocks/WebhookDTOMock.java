package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.WebhookDTO;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;

public class WebhookDTOMock {

    public static WebhookDTO getWebhookDto() {
        return new WebhookDTO()
                .setNumeroPedido(1)
                .setStatusPagamento(StatusPagamento.PAGO);
    }
}
