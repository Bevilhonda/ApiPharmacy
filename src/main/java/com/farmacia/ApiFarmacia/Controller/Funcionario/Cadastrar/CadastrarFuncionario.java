package com.farmacia.ApiFarmacia.Controller.Funcionario.Cadastrar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Cadastrar.DTO.RequisicaoDados;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.*;

@RestController
public class CadastrarFuncionario {

    @Autowired
    private ServicesFuncionario services;

    @PostMapping("/InsertFuncionario")
    public ResponseEntity<?> insert(@RequestBody @Valid RequisicaoDados funcionario) {
        try {
            services.cadastrarFuncionario(funcionario.toModel());

            return ResponseEntity.status(OK).build();

        } catch (Throwable excecao) {
            return mapearExcecoes(excecao);
        }
    }

}
