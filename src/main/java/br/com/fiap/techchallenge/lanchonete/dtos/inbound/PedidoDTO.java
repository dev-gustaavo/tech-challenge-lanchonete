package br.com.fiap.techchallenge.lanchonete.dtos.inbound;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoDTO {

    @Nullable
    private String identificacaoCliente;

    @NotNull
    private List<Integer> produtoId;
}
