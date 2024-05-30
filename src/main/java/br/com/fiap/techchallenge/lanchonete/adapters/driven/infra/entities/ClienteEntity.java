package br.com.fiap.techchallenge.lanchonete.adapters.driven.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column(nullable = false)
    private String cpf;
}
