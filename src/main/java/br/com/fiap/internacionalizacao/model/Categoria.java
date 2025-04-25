package br.com.fiap.internacionalizacao.model;

public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    FANTASIA("Fantasia"),
    BIOGRAFIA("Biografia"),
    TERROR("Terror");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
