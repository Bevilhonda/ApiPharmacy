package com.farmacia.ApiFarmacia.Controller.Funcionario.Atualizar.DTO;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class RequisicaoDeDados {
    @JsonProperty("id_funcionario")
    private Integer id_funcionario;
    @JsonProperty("nome")
    @NotBlank(message = "Digite o campo nome.")
    private String nome;
    @JsonProperty("sobrenome")
    @NotBlank(message = "Digite o campo sobrenome.")
    private String sobrenome;
    @JsonProperty("cargo")
    @NotBlank(message = "Digite o campo cargo.")
    private String cargo;

    public RequisicaoDeDados(Integer id_funcionario, String nome, String sobrenome, String cargo) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
    }

    public FuncionarioEntity toModel(){
        return new FuncionarioEntity(id_funcionario,nome,sobrenome,cargo);
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCargo() {
        return cargo;
    }
}
