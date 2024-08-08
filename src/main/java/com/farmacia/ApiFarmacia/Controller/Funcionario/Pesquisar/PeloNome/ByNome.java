package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloNome;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloNome.DTO.Response;
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
public class ByNome {
    @Autowired
    private ServicesFuncionario services;

    @GetMapping("/Nome/{nome}")
    public ResponseEntity<?> getNome(@PathVariable String nome) {
        try {
            List<FuncionarioEntity> listaNomes = services.getNome(nome);

            return ResponseEntity.status(OK).body(Response.from(listaNomes));

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }
}
