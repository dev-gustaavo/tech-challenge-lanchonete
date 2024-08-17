package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.QrCode;

public class QrCodeMock {

    public static QrCode getQrCode() {
        return new QrCode().setCodigo("123");
    }
}
