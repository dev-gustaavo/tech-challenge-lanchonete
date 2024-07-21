package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.QrCodeMapper;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.MercadoPagoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MercadoPagoGatewayImpl implements MercadoPagoGateway {

    private final QrCodeMapper qrCodeMapper;

    @Override
    public QrCode gerarQrCode(Integer numeroPedido) {
        return qrCodeMapper.toDomain("");
    }
}
