package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloIdFuncionario.ByIdFuncionario;
import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.FuncionarioNaoEncontrado;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@RunWith(SpringRunner.class)
@WebMvcTest(ByIdFuncionario.class)
class ByIdFuncionarioTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicesFuncionario services;

    @Test
    void getFuncionarioById() throws Exception, FuncionarioNaoEncontrado {

        FuncionarioEntity funcionario = new FuncionarioEntity
                (
                1,
                "Pedro",
                "Santos",
                "Atendente");

        when(services.getById(1)).thenReturn(funcionario);
        this.mockMvc.perform(get("/Funcionario/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Pedro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Santos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargo").value("Atendente"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

    }
    @Test
    void requisicaoPorIdNaoCompleta() throws Exception,FuncionarioNaoEncontrado{

        when(services.getById(1))
                .thenThrow(new FuncionarioNaoEncontrado(1)); // thenThrow = Então lance

        mockMvc.perform(get("/Funcionario/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}