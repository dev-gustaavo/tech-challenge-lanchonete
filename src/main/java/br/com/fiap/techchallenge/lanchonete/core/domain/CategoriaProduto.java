package br.com.fiap.techchallenge.lanchonete.core.domain;

public enum CategoriaProduto {
    LANCHE(1),
    ACOMPANHAMENTO(2),
    BEBIDA(3);

    private final int codigo;

    CategoriaProduto(int codigo) {
        this.codigo = codigo;
    }

    public static CategoriaProduto fromString(String text) {
        for (CategoriaProduto categoria : CategoriaProduto.values()) {
            if (categoria.name().equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria de produto inválida: " + text);
    }
}
