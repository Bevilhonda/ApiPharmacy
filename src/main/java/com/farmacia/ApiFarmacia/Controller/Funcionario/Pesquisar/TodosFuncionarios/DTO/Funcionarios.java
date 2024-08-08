package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.TodosFuncionarios.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

import java.util.List;

public record Funcionarios(Integer id, String nome, String sobrenome, String cargo) {

    public static Funcionarios from(FuncionarioEntity funcionario){

        return new Funcionarios(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCargo()
        );
    }
}
