package com.farmacia.ApiFarmacia.Controller.Funcionario.Cadastrar.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class RequisicaoDados {

    @JsonProperty("id_funcionarios")
    private Integer idFuncionario;
    @JsonProperty("nome")
    @NotBlank(message = "Digite o nome do Funcionário.")
    private String nome;
    @JsonProperty("sobrenome")
    @NotBlank(message = "Digite o sobrenome do Funcionário.")
    private String sobrenome;
    @JsonProperty("cargo")
    @NotBlank(message = "Digite o cargo do Funcionário.")
    private String cargo;

    public RequisicaoDados(String nome, String sobrenome, String cargo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
    }

    public FuncionarioEntity toModel(){

        return new FuncionarioEntity(idFuncionario, nome,sobrenome,cargo);

    }
}
