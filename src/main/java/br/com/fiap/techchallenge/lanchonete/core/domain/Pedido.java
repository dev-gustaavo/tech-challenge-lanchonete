package br.com.fiap.techchallenge.lanchonete.core.domain;

import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<Produto> produtos;
    private EtapaPedido etapaPedido;
}
