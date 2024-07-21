package br.com.fiap.techchallenge.lanchonete.interfaces.gateways;

import br.com.fiap.techchallenge.lanchonete.entities.QrCode;

public interface MercadoPagoGateway {
    QrCode gerarQrCode(Integer numeroPedido);
}
