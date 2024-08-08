package com.farmacia.ApiFarmacia.Controller.Funcionario.Cadastrar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Cadastrar.DTO.RequisicaoDados;
import com.farmacia.ApiFarmacia.Model.Funcionario.FuncionarioEntity;
import com.farmacia.ApiFarmacia.Service.Funcionario.ServicesFuncionario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CadastrarFuncionario.class)
class CadastrarFuncionarioTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ServicesFuncionario services;



    @Test
    void insert() throws Exception {

        RequisicaoDados requisicaoDados =
                new RequisicaoDados("Pedro","Batista","Caixa");

        this.mockMvc.perform(post("/InsertFuncionario")
                .content(objectMapper.writeValueAsString(requisicaoDados))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ArgumentCaptor<FuncionarioEntity> funcionarioCaptor =
                ArgumentCaptor.forClass(FuncionarioEntity.class);
        verify(services,times(1))
                .cadastrarFuncionario(funcionarioCaptor.capture());

        assertThat(funcionarioCaptor.getValue().getIdFuncionario()).isNull();
        assertThat(funcionarioCaptor.getValue().getNome()).isEqualTo("Pedro");
        assertThat(funcionarioCaptor.getValue().getSobrenome()).isEqualTo("Batista");
        assertThat(funcionarioCaptor.getValue().getCargo()).isEqualTo("Caixa");

    }

    @Test
    void validaParametroNome() throws Exception{

        RequisicaoDados requisicaoDados =
                new RequisicaoDados(null,"Batista","Caixa");

        this.mockMvc.perform(post("/InsertFuncionario")
                .content(objectMapper.writeValueAsString(requisicaoDados))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"nome\":\"Digite o nome do Funcionário.\"}"));

        verify(services,never()).cadastrarFuncionario(any(FuncionarioEntity.class));

    }

    @Test
    void validaParametroSobrenome() throws Exception{

        RequisicaoDados requisicaoDados =
                new RequisicaoDados("Pedro",null,"Caixa");

        this.mockMvc.perform(post("/InsertFuncionario")
                        .content(objectMapper.writeValueAsString(requisicaoDados))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"sobrenome\":\"Digite o sobrenome do Funcionário.\"}"));

        verify(services,never()).cadastrarFuncionario(any(FuncionarioEntity.class));

    }

    @Test
    void validaParametroCargo() throws Exception{

        RequisicaoDados requisicaoDados =
                new RequisicaoDados("Pedro","Batista",null);

        this.mockMvc.perform(post("/InsertFuncionario")
                        .content(objectMapper.writeValueAsString(requisicaoDados))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"cargo\":\"Digite o cargo do Funcionário.\"}"));

        verify(services,never()).cadastrarFuncionario(any(FuncionarioEntity.class));

    }



}