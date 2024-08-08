package com.farmacia.ApiFarmacia.Model.Medicamento;

public class Medicamento {
    String nome;
    Float valor;
    Integer quantidadeTotal;

    public Medicamento(String nome, Float valor, Integer quantidadeTotal) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeTotal = quantidadeTotal;
    }

    public String getNome() {
        return nome;
    }

    public Float getValor() {
        return valor;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }
}
