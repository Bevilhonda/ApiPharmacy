package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloIdFuncionario;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloIdFuncionario.DTO.Response.Response;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes.ManipuladorExcecoes.mapearExcecoes;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class ByIdFuncionario {
    @Autowired
    private ServicesFuncionario servicesFuncionario;

    @GetMapping("/Funcionario/{idFuncionario}")
    public ResponseEntity<?> getFuncionarioById(@PathVariable Integer idFuncionario) {
        try {
            FuncionarioEntity funcionario = servicesFuncionario.getById(idFuncionario);

            return ResponseEntity.status(OK).body(Response.from(funcionario));


        } catch (Throwable e) {
            return mapearExcecoes(e);
        }
    }

}
