package com.farmacia.ApiFarmacia.Model.Funcionario;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionarios")
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idFuncionarios;

    private String nome;

    private String sobrenome;

    private String cargo;


    public FuncionarioEntity() {
    }

    public FuncionarioEntity(Integer id_funcionarios , String nome, String sobrenome, String cargo) {
        this.idFuncionarios = id_funcionarios;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;

    }

    public Integer getIdFuncionario() {
        return idFuncionarios;
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
