package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.repositories.cliente;

import br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCliente extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findByCpf(String cpf);
}
