package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.dtos.inbound.ClienteDTO;

public class ClienteDTOMock {

    public static ClienteDTO getClienteDTO() {
        return new ClienteDTO()
                .setNome("Gustavo")
                .setCpf("01234567890")
                .setEmail("teste@teste.com.br");
    }
}
