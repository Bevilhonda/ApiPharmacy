package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloNome.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

import java.util.List;

public record Response(List<Funcionario> lista) {

    public static Response from(List<FuncionarioEntity> funcionarios){

        List<Funcionario> listFuncionario = funcionarios
                .stream()
                .map(nome -> Funcionario.from(nome)).toList();

        return new Response(listFuncionario);
    }
}
