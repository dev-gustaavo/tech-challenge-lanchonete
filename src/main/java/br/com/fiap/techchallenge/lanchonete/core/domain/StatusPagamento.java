package br.com.fiap.techchallenge.lanchonete.core.domain;

public enum StatusPagamento {
    PENDENTE(0),
    PAGO(1);

    private final int codigo;

    StatusPagamento(int codigo) {
        this.codigo = codigo;
    }
}
