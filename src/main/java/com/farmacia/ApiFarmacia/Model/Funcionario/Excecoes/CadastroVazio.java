package com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes;

public class CadastroVazio extends FuncionarioExceptions {

    private final String mensagem;

    public CadastroVazio() {
        mensagem = "Nenhum funcionario foi encontrado.";
    }

    public String getMessage() {
        return mensagem;
    }
}
