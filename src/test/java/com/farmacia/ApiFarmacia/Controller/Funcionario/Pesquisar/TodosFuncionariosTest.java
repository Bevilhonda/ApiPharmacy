package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.TodosFuncionarios.TodosFuncionarios;
import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.CadastroVazio;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TodosFuncionarios.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TodosFuncionariosTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicesFuncionario services;

    @Test
    void allFuncionarios() throws Exception, CadastroVazio {
        FuncionarioEntity funcionario = new FuncionarioEntity
                (1, "Pedro", "Santos", "Atendente");
        FuncionarioEntity funcionario2 = new FuncionarioEntity
                (2, "Jorge", "Santos", "Atendente");

        List<FuncionarioEntity> listaVazia = new ArrayList<>();
        listaVazia.add(funcionario);
        listaVazia.add(funcionario2);

        when(services.todosFuncionarios()).thenReturn(listaVazia);
        this.mockMvc.perform(get("/TodosFuncionarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista", Matchers.hasSize(2)))
                .andReturn();

    }
}