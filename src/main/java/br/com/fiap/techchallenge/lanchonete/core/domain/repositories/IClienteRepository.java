package br.com.fiap.techchallenge.lanchonete.core.domain.repositories;

import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;

import java.util.List;

public interface IClienteRepository {

    Cliente save(Cliente cliente) throws Exception;

    List<Cliente> list();

    Cliente updateCliente(Integer id, Cliente cliente);

    void delete(Integer id);
}
