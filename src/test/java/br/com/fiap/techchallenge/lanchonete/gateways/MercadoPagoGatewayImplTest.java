package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.adapters.mappers.QrCodeMapper;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;
import br.com.fiap.techchallenge.lanchonete.mocks.QrCodeMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class MercadoPagoGatewayImplTest {

    @Mock
    private QrCodeMapper qrCodeMapper;

    @InjectMocks
    private MercadoPagoGatewayImpl mercadoPagoGateway;

    private final QrCode qrCodeMock = QrCodeMock.getQrCode();

    @Test
    @Description("Deve retornar QR Code para pagamento")
    void deveRetornarQrCode() {
        when(qrCodeMapper.toDomain(any())).thenReturn(qrCodeMock);

        var result = mercadoPagoGateway.gerarQrCode(1);

        verify(qrCodeMapper, times(1)).toDomain(any());
        assertNotNull(result);
    }
}
