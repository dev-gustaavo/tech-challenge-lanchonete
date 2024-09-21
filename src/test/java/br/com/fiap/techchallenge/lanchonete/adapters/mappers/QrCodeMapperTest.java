package br.com.fiap.techchallenge.lanchonete.adapters.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class QrCodeMapperTest {

    private QrCodeMapper qrCodeMapper = QrCodeMapper.INSTANCE;

    @Test
    @Description("Deve retornar null quando o qrCode for null")
    void deveRetornarNullQuandoQrCodeForNull() {
        assertNull(qrCodeMapper.toDomain(null));
    }

    @Test
    @Description("Deve retornar o QR Code")
    void deveRetornarQrCode() {
        assertNotNull(qrCodeMapper.toDomain("qrcode"));
    }
}
