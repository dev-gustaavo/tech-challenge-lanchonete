package br.com.fiap.techchallenge.lanchonete.adapters.dto;

import br.com.fiap.techchallenge.lanchonete.core.domain.Cliente;
import br.com.fiap.techchallenge.lanchonete.core.domain.Produto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoDTO {

    @Nullable
    private Cliente cliente;

    @NotNull
    private List<Produto> produtos;
}
