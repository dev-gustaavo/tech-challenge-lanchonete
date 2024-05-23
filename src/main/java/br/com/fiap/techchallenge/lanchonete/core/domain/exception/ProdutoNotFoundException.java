package br.com.fiap.techchallenge.lanchonete.core.domain.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
