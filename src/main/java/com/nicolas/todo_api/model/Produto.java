package com.nicolas.todo_api.model;

public class Produto {
    private String titulo;
    private double preco;
    private String disponibilidade;
    private String url;

    public Produto(String titulo, double preco, String disponibilidade, String url) {
        this.titulo = titulo;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
        this.url = url;
    }

    public String getTitulo() { return titulo; }
    public double getPreco() { return preco; }
    public String getDisponibilidade() { return disponibilidade; }
    public String getUrl() { return url; }

    @Override
    public String toString() {
        return String.format("📦 %s%n   💰 R$ %.2f%n   🔗 %s", titulo, preco, url);
    }
}
