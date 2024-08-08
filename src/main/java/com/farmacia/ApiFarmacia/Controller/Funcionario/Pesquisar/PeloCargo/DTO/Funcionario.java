package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloCargo.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

public record Funcionario(Integer id, String nome, String sobrenome, String cargo) {

    public static Funcionario from(FuncionarioEntity funcionario) {
        return new Funcionario(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCargo());
    }
}
