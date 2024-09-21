package br.com.fiap.techchallenge.lanchonete.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorProdutoInexistenteResponse {
    private int status;
    private String message;
    private Map<Integer, Boolean> idProdutos;
}
