package com.farmacia.ApiFarmacia.Controller.Funcionario.ManipuladorDeExcecoes;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.*;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ManipuladorExcecoes {

    public static ResponseEntity<?> mapearExcecoes(Throwable padraoDeExcecoes) {
        /* quando a exceção for alguns dos casos ,vai converter a exceção padrão,
         chamada de pattern para o tipo personalizado descrito na string */

        switch (padraoDeExcecoes.getClass().getSimpleName()) {
            case "FuncionarioNaoEncontrado" -> {
                return convertExcecaoPadrao((FuncionarioNaoEncontrado) padraoDeExcecoes);
            }
            case "NomeNaoEncontrado" -> {
                return convertExcecaoPadrao((NomeNaoEncontrado) padraoDeExcecoes);
            }
            case "SobrenomeNaoEncontrado" -> {
                return convertExcecaoPadrao((SobrenomeNaoEncontrado) padraoDeExcecoes);
            }
            case "CargoNaoEncontrado" -> {
                return convertExcecaoPadrao((CargoNaoEncontrado) padraoDeExcecoes);
            }
            case "CadastroVazio" -> {
                return convertExcecaoPadrao((CadastroVazio) padraoDeExcecoes);
            }
            default -> {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(padraoDeExcecoes.getMessage());
                //quando nenhum dos casos no switch corresponde a uma exceção específica criada,
                // ele cai no caso padraoDeExcecoes(default)  e cria uma resposta de erro com status
                // INTERNAL_SERVER_ERROR ,  e usa padraoDeExcecoes.getMessage() para obter a mensagem
                // de erro da exceção não tratada.
                // Se a exceção não tiver uma mensagem personalizada, o método .getMessage()
                // retornará null ou uma representação padrão da exceção,
                // que pode não ser muito informativa.
            }

        }

    }

    public static ResponseEntity<?> convertExcecaoPadrao(FuncionarioNaoEncontrado funcionarioNaoEncontrado) {
        return ResponseEntity.status(NOT_FOUND).body(funcionarioNaoEncontrado.getMessage());
    }

    public static ResponseEntity<?> convertExcecaoPadrao(NomeNaoEncontrado nomeNaoEncontrado){
        return ResponseEntity.status(NOT_FOUND).body(nomeNaoEncontrado.getMessage());
    }

    public static ResponseEntity<?> convertExcecaoPadrao(SobrenomeNaoEncontrado sobrenomeNaoEncontrado) {
        return ResponseEntity.status(NOT_FOUND).body(sobrenomeNaoEncontrado.getMessage());
    }

    public static ResponseEntity<?> convertExcecaoPadrao(CargoNaoEncontrado cargoNaoEncontrado) {
        return ResponseEntity.status(NOT_FOUND).body(cargoNaoEncontrado.getMessage());
    }

    public static ResponseEntity<?> convertExcecaoPadrao(CadastroVazio cadastroNaoEncontrado) {
        return ResponseEntity.status(NOT_FOUND).body(cadastroNaoEncontrado.getMessage());
    }
}
