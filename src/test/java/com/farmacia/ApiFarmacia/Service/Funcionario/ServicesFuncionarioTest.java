package com.farmacia.ApiFarmacia.Service.Funcionario;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.*;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@DataJpaTest
@ComponentScan()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ServicesFuncionarioTest {
    @Autowired
    private ServicesFuncionario servicesFuncionario;

    @Test
    void cadastrarFuncionario() throws NomeNaoEncontrado {
        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");

        servicesFuncionario.cadastrarFuncionario(funcionario);

    }

    @Test
    void buscarFuncionarioById() throws FuncionarioNaoEncontrado {

        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");

        servicesFuncionario.cadastrarFuncionario(funcionario);
        FuncionarioEntity funcionario1 = servicesFuncionario.getById(1);
        assertThat(funcionario1.getNome()).isEqualTo(funcionario.getNome());
        assertThat(funcionario1.getSobrenome()).isEqualTo(funcionario.getSobrenome());
        assertThat(funcionario1.getCargo()).isEqualTo(funcionario.getCargo());
    }

    @Test
    void buscarFuncionarioPorNome() throws NomeNaoEncontrado {

        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");
        servicesFuncionario.cadastrarFuncionario(funcionario);
        List<FuncionarioEntity> listaNomes = servicesFuncionario.getNome("Pedro");

        assertThat(listaNomes.get(0).getNome()).isEqualTo("Pedro");
    }

    @Test
    void buscaPorSobrenome() throws SobrenomeNaoEncontrado {
        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");
        servicesFuncionario.cadastrarFuncionario(funcionario);

        List<FuncionarioEntity> listaNomes = servicesFuncionario.getSobrenome("Batista");

        assertThat(listaNomes.get(0).getSobrenome()).isEqualTo("Batista");

    }

    @Test
    void buscaPorCargo() throws CargoNaoEncontrado {
        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");
        servicesFuncionario.cadastrarFuncionario(funcionario);

        List<FuncionarioEntity> listaNomes = servicesFuncionario.getCargo("Farmaceutico");

        assertThat(listaNomes.get(0).getCargo()).isEqualTo("Farmaceutico");

    }

    @Test
    void atualizarFuncionario() throws FuncionarioNaoEncontrado, CadastroVazio {
        FuncionarioEntity funcionario =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");

        FuncionarioEntity funcionarioAtual =
                new FuncionarioEntity(null, "Jorge", "Santos", "Atendente");

        servicesFuncionario.cadastrarFuncionario(funcionario);

        servicesFuncionario.atualizaFuncionario(1, funcionarioAtual);

        List<FuncionarioEntity> listaFuncionarios = servicesFuncionario.todosFuncionarios();
        assertThat(listaFuncionarios.size()).isEqualTo(1);

        assertThat(listaFuncionarios.get(0).getNome()).isEqualTo(funcionarioAtual.getNome());
        assertThat(listaFuncionarios.get(0).getSobrenome()).isEqualTo(funcionarioAtual.getSobrenome());
        assertThat(listaFuncionarios.get(0).getCargo()).isEqualTo(funcionarioAtual.getCargo());

    }

    @Test
    void deletarFuncionario() throws CadastroVazio, FuncionarioNaoEncontrado {
        FuncionarioEntity funcionario1 =
                new FuncionarioEntity(null, "Pedro", "Batista", "Farmaceutico");

        FuncionarioEntity funcionario2 =
                new FuncionarioEntity(null, "Jorge", "Santos", "Atendente");

        servicesFuncionario.cadastrarFuncionario(funcionario1);
        servicesFuncionario.cadastrarFuncionario(funcionario2);

        List<FuncionarioEntity> listaFuncionarios = servicesFuncionario.todosFuncionarios();
        assertThat(listaFuncionarios.size()).isEqualTo(2);

        servicesFuncionario.excluiFuncionario(1);
        FuncionarioEntity funcionarioAtual = servicesFuncionario.getById(2);

        List<FuncionarioEntity> listaFuncionarios2 = servicesFuncionario.todosFuncionarios();

        assertThat(listaFuncionarios2.size()).isEqualTo(1);

        assertThat(listaFuncionarios2.get(0).getNome()).isEqualTo(funcionarioAtual.getNome());
        assertThat(listaFuncionarios2.get(0).getSobrenome()).isEqualTo(funcionarioAtual.getSobrenome());
        assertThat(listaFuncionarios2.get(0).getCargo()).isEqualTo(funcionarioAtual.getCargo());

    }

    @Test
    void validaServicoAtualizaFuncionario() {

        FuncionarioEntity funcionario2 =
                new FuncionarioEntity(null, "Jorge", "Santos", "Atendente");

        Throwable excecao = catchThrowable(() -> servicesFuncionario.atualizaFuncionario(1, funcionario2));

        assertThat(excecao).isInstanceOf(FuncionarioNaoEncontrado.class)
                .hasMessageContaining("O Funcionario com o id 1 não foi encontrado.");

    }

    @Test
    void validaServicoExcluiFuncionario() {

        Throwable exception = catchThrowable(() -> servicesFuncionario.excluiFuncionario(1));

        assertThat(exception)
                .isInstanceOf(FuncionarioNaoEncontrado.class)
                .hasMessageContaining("O Funcionario com o id 1 não foi encontrado.");

    }
}