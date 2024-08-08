package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.TodosFuncionarios.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

import java.util.List;

public record Response(List<Funcionarios> lista) {

    public static Response from(List<FuncionarioEntity> listaNomes) {

        List<Funcionarios> lista = listaNomes
                .stream()
                .map(nome -> Funcionarios.from(nome)).toList();

        return new Response(lista);

    }
}
