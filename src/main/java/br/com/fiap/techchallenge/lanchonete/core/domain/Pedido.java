package br.com.fiap.techchallenge.lanchonete.core.domain;

import java.util.List;

public class Pedido {
    private String identificacaoCliente;
    private List<Integer> produtoId;
    private EtapaPedido etapaPedido = EtapaPedido.RECEBIDO;
    private StatusPagamento statusPagamento = StatusPagamento.PENDENTE;

    public String getIdentificacaoCliente() {
        return identificacaoCliente;
    }

    public void setIdentificacaoCliente(String identificacaoCliente) {
        this.identificacaoCliente = identificacaoCliente;
    }

    public List<Integer> getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(List<Integer> produtoId) {
        this.produtoId = produtoId;
    }

    public EtapaPedido getEtapaPedido() {
        return etapaPedido;
    }

    public void setEtapaPedido(EtapaPedido etapaPedido) {
        this.etapaPedido = etapaPedido;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
