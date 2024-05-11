package br.com.fiap.techchallenge.lanchonete.core.application.usecases;

import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;

import java.util.List;

public interface IClienteUseCase {

    Cliente save(Cliente cliente);

    List<Cliente> list();

    Cliente update(Integer id, Cliente cliente);

    void delete(Integer id);
}
