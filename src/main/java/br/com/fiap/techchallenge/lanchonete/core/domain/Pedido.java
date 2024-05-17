package br.com.fiap.techchallenge.lanchonete.core.domain;

import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Produto> itens;
    private EtapaPedido etapaPedido;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    public EtapaPedido getEtapaPedido() {
        return etapaPedido;
    }

    public void setEtapaPedido(EtapaPedido etapaPedido) {
        this.etapaPedido = etapaPedido;
    }
}
