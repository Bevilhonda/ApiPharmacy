package com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes;

public class FuncionarioNaoEncontrado extends FuncionarioExceptions{

    private final Integer id ;
    private final String mnesagem;

    public FuncionarioNaoEncontrado(Integer id) {
        this.id = id;
        mnesagem = "O Funcionario com o id " + getId() + " não foi encontrado.";
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return mnesagem;
    }
}
