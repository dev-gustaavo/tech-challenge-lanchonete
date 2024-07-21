package br.com.fiap.techchallenge.lanchonete.interfaces.dbconnection;

import br.com.fiap.techchallenge.lanchonete.entities.dbEntities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryPedido extends JpaRepository<PedidoEntity, Integer> {
    Optional<List<PedidoEntity>> findByStatusPagamento(String statusPagamento);

    @Query("SELECT p FROM PedidoEntity p " +
            "WHERE p.etapaPedido != 'FINALIZADO' " +
            "ORDER BY CASE p.etapaPedido " +
            "WHEN 'PRONTO' THEN 1 " +
            "WHEN 'EM_PREPARACAO' THEN 2 " +
            "WHEN 'RECEBIDO' THEN 3 " +
            "ELSE 4 END, " +
            "p.createdAt ASC")
    Optional<List<PedidoEntity>> listPedidosOrdenadoPorStatus();
}
