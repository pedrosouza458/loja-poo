package org.example;

public class Produto {
    private String nome;
    private Double preco;
    private CategoriaProduto categoriaProduto;

    public Produto(String nome, Double preco, CategoriaProduto categoriaProduto) {
        this.nome = nome;
        this.preco = preco;
        this.categoriaProduto = categoriaProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
}
