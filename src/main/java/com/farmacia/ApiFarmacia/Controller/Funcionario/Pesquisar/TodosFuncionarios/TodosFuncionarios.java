package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.TodosFuncionarios;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.TodosFuncionarios.DTO.Response;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TodosFuncionarios {
    @Autowired
    private ServicesFuncionario services;

    @GetMapping("/TodosFuncionarios")
    public ResponseEntity<?> allFuncionarios() {

        try {
            List<FuncionarioEntity> listaFuncionarios = services.todosFuncionarios();

            return ResponseEntity.status(OK).body(Response.from(listaFuncionarios));

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }
}
