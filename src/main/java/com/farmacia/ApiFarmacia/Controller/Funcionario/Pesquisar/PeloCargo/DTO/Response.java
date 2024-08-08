package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloCargo.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

import java.util.List;

public record Response(List<Funcionario> lista) {

    public static Response from(List<FuncionarioEntity> listaCargos) {

        List<Funcionario> listFuncionarios = listaCargos
                .stream()
                .map(cargo -> Funcionario.from(cargo)).toList();

        return new Response(listFuncionarios);
    }
}
