package br.com.fiap.techchallenge.lanchonete.entities.exception;

import java.util.Map;

public class ProdutoException extends RuntimeException {

    private final Map<Integer, Boolean> idProdutos;

    public ProdutoException(String message) {
        super(message);
        this.idProdutos = null;
    }

    public ProdutoException(String message, Map<Integer, Boolean> idProdutos) {
        super(message);
        this.idProdutos = idProdutos;
    }

    public Map<Integer, Boolean> getIdProdutos() {
        return idProdutos;
    }
}
