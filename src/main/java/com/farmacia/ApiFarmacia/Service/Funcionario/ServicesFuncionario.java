package com.farmacia.ApiFarmacia.Service.Funcionario;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.*;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Repository.RepositoryFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesFuncionario {
    @Autowired
    private RepositoryFuncionario repositoryFuncionario;

    public void cadastrarFuncionario(FuncionarioEntity funcionario) {

        repositoryFuncionario.incluirFuncionario(
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCargo());
    }

    public FuncionarioEntity getById(Integer id) throws FuncionarioNaoEncontrado {

        FuncionarioEntity funcionario = repositoryFuncionario.getFuncionarioById(id);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontrado(id);
        }
        return funcionario;
    }

    public List<FuncionarioEntity> getNome(String nome) throws NomeNaoEncontrado {
        List<FuncionarioEntity> listaNomes = repositoryFuncionario.buscaPorNome(nome);

        if (listaNomes.isEmpty()) {
            throw new NomeNaoEncontrado(nome);

        }
        return listaNomes;
    }

    public List<FuncionarioEntity> getSobrenome(String sobrenome) throws SobrenomeNaoEncontrado {
        List<FuncionarioEntity> listaFuncionarios = repositoryFuncionario.buscaPorSobrenome(sobrenome);

        if (listaFuncionarios.isEmpty()) {
            throw new SobrenomeNaoEncontrado(sobrenome);
        }
        return listaFuncionarios;

    }

    public List<FuncionarioEntity> getCargo(String cargo) throws CargoNaoEncontrado {

        List<FuncionarioEntity> listaFuncionariosCargo = repositoryFuncionario.buscaPorCargo(cargo);

        if (listaFuncionariosCargo.isEmpty()) {
            throw new CargoNaoEncontrado(cargo);
        }
        return listaFuncionariosCargo;
    }

    public List<FuncionarioEntity> todosFuncionarios() throws CadastroVazio {
        List<FuncionarioEntity> listaFuncionarios = repositoryFuncionario.todosFuncionarios();

        if (listaFuncionarios.isEmpty()) {
            throw new CadastroVazio();

        }
        return listaFuncionarios;
    }

    public void atualizaFuncionario(
            Integer id_funcionario, FuncionarioEntity funcionario) throws FuncionarioNaoEncontrado {

        FuncionarioEntity dadosFuncionario = repositoryFuncionario.getFuncionarioById(id_funcionario);

        if (dadosFuncionario == null) {
            throw new FuncionarioNaoEncontrado(id_funcionario);
        }
        repositoryFuncionario.atualizaFuncionario(
                funcionario.getNome(),
                funcionario.getSobrenome(),
                funcionario.getCargo()
                , id_funcionario);

    }

    public void excluiFuncionario(Integer id_funcionario) throws FuncionarioNaoEncontrado {

        FuncionarioEntity dadosFuncionario = repositoryFuncionario.getFuncionarioById(id_funcionario);

        if (dadosFuncionario == null) {

            throw new FuncionarioNaoEncontrado(id_funcionario);

        }

        repositoryFuncionario.excluirFuncionario(id_funcionario);
    }
}
