package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.Cliente;

public class ClienteMock {

    public static Cliente getCliente() {
        return new Cliente()
                .setNome("Gustavo")
                .setCpf("01234567890")
                .setEmail("teste@teste.com.br");
    }
}
