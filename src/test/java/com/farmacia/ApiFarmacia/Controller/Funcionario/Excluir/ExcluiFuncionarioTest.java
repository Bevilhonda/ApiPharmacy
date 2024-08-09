package com.farmacia.ApiFarmacia.Controller.Funcionario.Excluir;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.FuncionarioNaoEncontrado;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExcluiFuncionario.class)
class ExcluiFuncionarioTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicesFuncionario services;

    @Test
    void excluirFuncionario() throws Exception, FuncionarioNaoEncontrado {

        mockMvc.perform(MockMvcRequestBuilders.delete("/Excluir/{id_funcionario}",1))
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(services).excluiFuncionario(1);

        Mockito.verify(services,Mockito.times(1)).excluiFuncionario(1);


    }

    @Test
    void validacaoNaoCompleta() throws Exception,FuncionarioNaoEncontrado{

        doThrow(new FuncionarioNaoEncontrado(1))
                .when(services).excluiFuncionario(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/Excluir/{id_funcionario}",1))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(services ,times(1)).excluiFuncionario(1);

    }
}