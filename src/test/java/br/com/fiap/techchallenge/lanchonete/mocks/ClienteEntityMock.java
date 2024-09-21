package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;

public class ClienteEntityMock {

    public static ClienteEntity getClienteEntity() {
        return new ClienteEntity()
                .setNome("nome")
                .setEmail("email")
                .setCpf("01234567890");
    }
}
