package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloSobrenome.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;

import java.util.List;

public record Response(List<Funcionario> lista) {

    public static Response from(List<FuncionarioEntity> listaSobrenome){

        List<Funcionario> funcionarios = listaSobrenome
                .stream()
                .map(sobrenome -> Funcionario.from(sobrenome)).toList();

        return  new Response(funcionarios);
    }
}
