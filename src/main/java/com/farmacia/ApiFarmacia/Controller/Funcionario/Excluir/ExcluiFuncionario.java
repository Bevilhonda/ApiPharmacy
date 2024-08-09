package com.farmacia.ApiFarmacia.Controller.Funcionario.Excluir;

import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class ExcluiFuncionario {

    @Autowired
    private ServicesFuncionario services;

    @DeleteMapping("/Excluir/{id_funcionario}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Integer id_funcionario) {
        try {
            services.excluiFuncionario(id_funcionario);

            return ResponseEntity.status(OK).build();

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }
}
