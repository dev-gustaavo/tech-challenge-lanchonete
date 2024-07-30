package br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection;

import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCliente extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findByCpf(String cpf);
}
