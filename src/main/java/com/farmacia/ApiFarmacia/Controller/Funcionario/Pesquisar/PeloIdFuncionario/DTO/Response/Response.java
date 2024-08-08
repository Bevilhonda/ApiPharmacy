package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloIdFuncionario.DTO.Response;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

public record Response(Integer id , String nome, String sobrenome, String cargo) {

    public static Response from(FuncionarioEntity funcionario){

        return new Response(funcionario.getIdFuncionario(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getCargo());

    }
}
