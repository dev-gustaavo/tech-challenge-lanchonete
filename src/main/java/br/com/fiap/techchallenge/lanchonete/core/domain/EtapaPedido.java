package br.com.fiap.techchallenge.lanchonete.core.domain;

public enum EtapaPedido {
    RECEBIDO(1),
    EM_PREPARACAO(2),
    PRONTO(3),
    FINALIZADO(4);

    private final int codigo;

    EtapaPedido(int codigo) {
        this.codigo = codigo;
    }
}
