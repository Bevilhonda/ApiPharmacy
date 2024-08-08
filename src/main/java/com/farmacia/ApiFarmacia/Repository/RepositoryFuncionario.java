package com.farmacia.ApiFarmacia.Repository;

import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Transactional
@Service
public interface RepositoryFuncionario extends JpaRepository<FuncionarioEntity, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(value = "Insert into Funcionarios (nome , sobrenome, cargo ) " +
            "values (:nome , :sobrenome , :cargo )", nativeQuery = true)
    Integer incluirFuncionario(String nome, String sobrenome, String cargo);

    @Query(value = "Select * from Funcionarios where id_funcionarios = :idFuncionarios", nativeQuery = true)
    FuncionarioEntity getFuncionarioById(Integer idFuncionarios);

    @Query(value = "Select * from Funcionarios where nome = :nome", nativeQuery = true)
    List<FuncionarioEntity> buscaPorNome(String nome);

    @Query(value = "Select * from Funcionarios where sobrenome = :sobrenome", nativeQuery = true)
    List<FuncionarioEntity> buscaPorSobrenome(String sobrenome);

    @Query(value = "Select * from Funcionarios where cargo = :cargo", nativeQuery = true)
    List<FuncionarioEntity> buscaPorCargo(String cargo);

    @Query(value = "Select * from Funcionarios", nativeQuery = true)
    List<FuncionarioEntity> todosFuncionarios();

    @Modifying(clearAutomatically = true)
    @Query(value = "Update Funcionarios set " +
            "nome = :nome , " +
            "sobrenome = :sobrenome ," +
            " cargo = :cargo" +
            " where id_funcionarios = :id_funcionarios", nativeQuery = true)
    Integer atualizaFuncionario(
            String nome, String sobrenome, String cargo, Integer id_funcionarios);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Funcionarios where id_funcionarios = :id_funcionario",nativeQuery = true)
    Integer excluirFuncionario(Integer id_funcionario);

}
