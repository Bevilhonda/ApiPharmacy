package com.farmacia.ApiFarmacia.Controller.Funcionario.Atualizar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Atualizar.DTO.RequisicaoDeDados;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class AtualizarFuncionario {
    @Autowired
    private ServicesFuncionario services;

    @PutMapping("/Atualiza/{id_funcionario}")
    public ResponseEntity<?> atualizaFuncionario(
            @PathVariable Integer id_funcionario, @RequestBody @Valid RequisicaoDeDados dadosFuncionario){
        try {
            services.atualizaFuncionario(id_funcionario,dadosFuncionario.toModel());

            return ResponseEntity.status(OK).build();

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }
}
