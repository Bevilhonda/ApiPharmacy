package com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes;

public class SobrenomeNaoEncontrado extends FuncionarioExceptions{
   

    private final String mensagem;
    private final String sobrenome;

    public SobrenomeNaoEncontrado(String sobrenome) {
        this.sobrenome = sobrenome;
        mensagem = "Não foi encontrado nenhum funcionario com o sobrenome " + getSobrenome();

    }

    public String getMessage() {
        return mensagem;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}
