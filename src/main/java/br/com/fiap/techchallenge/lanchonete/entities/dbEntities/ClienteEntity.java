package br.com.fiap.techchallenge.lanchonete.entities.dbEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"cpf"})
        }
)
@Getter
@Setter
@Accessors(chain = true)
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;
}
