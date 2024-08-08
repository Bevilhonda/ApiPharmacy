package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloSobrenome;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloSobrenome.DTO.Response;
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
public class BySobrenome {
    @Autowired
    private ServicesFuncionario services ;

    @GetMapping("/Sobrenome/{sobrenome}")
    public ResponseEntity<?> getSobrenome(@PathVariable String sobrenome){

        try {
            List<FuncionarioEntity> listaSobrenome = services.getSobrenome(sobrenome);

            return ResponseEntity.status(OK).body(Response.from(listaSobrenome));

        } catch (Throwable e) {
            return mapearExcecoes(e);
        }

    }
}
