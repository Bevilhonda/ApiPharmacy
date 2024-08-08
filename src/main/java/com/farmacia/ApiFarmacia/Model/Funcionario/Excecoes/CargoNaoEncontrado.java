package com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes;

public class CargoNaoEncontrado extends FuncionarioExceptions{

     private final String mensagem;
     private final String cargo;

     public CargoNaoEncontrado(String cargo){
         this.cargo = cargo;
         mensagem = "Não foi encontrado nenhum cargo com o nome " + getCargo() ;
     }

    public String getMessage() {
        return mensagem;
    }

    public String getCargo() {
        return cargo;
    }
}
