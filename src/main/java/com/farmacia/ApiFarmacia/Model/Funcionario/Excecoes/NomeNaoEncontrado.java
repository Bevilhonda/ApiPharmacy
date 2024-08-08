package com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes;


public class NomeNaoEncontrado extends FuncionarioExceptions{


    private final String mensagem;
    private final String nome;

    public NomeNaoEncontrado(String nome) {
        this.nome = nome;
        mensagem = "Não foi encontrado nenhum funcionario com o nome " + getNome();

    }

    public String getMessage() {
        return mensagem;
    }

    public String getNome() {
        return nome;
    }
}
