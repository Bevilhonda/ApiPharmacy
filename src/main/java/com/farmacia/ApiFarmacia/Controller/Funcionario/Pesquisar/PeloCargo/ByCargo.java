package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloCargo;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloCargo.DTO.Response;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class ByCargo {
    @Autowired
    private ServicesFuncionario services;

    @GetMapping("/Cargo/{cargo}")
    public ResponseEntity<?> byCargo(@PathVariable String cargo) {
        try {
            List<FuncionarioEntity> listaCargos = services.getCargo(cargo);

            return ResponseEntity.status(OK).body(Response.from(listaCargos));

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }
}
