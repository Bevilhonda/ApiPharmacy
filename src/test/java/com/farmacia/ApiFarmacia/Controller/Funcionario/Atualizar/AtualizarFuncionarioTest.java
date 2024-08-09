package com.farmacia.ApiFarmacia.Controller.Funcionario.Atualizar;

import com.farmacia.ApiFarmacia.Controller.Funcionario.Atualizar.DTO.RequisicaoDeDados;
import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.FuncionarioNaoEncontrado;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AtualizarFuncionario.class)
class AtualizarFuncionarioTest {
    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private ServicesFuncionario services;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    public void atualizaFuncionarioTest() throws Exception , FuncionarioNaoEncontrado {

        RequisicaoDeDados dadosObtidos =
                new RequisicaoDeDados(
                        1,
                        "Pedro",
                        "Santos",
                        "Farmaceutico");

        mockMvc.perform(put("/Atualiza/{id_funcionario}",1)
                .content(objectMapper.writeValueAsString(dadosObtidos))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ArgumentCaptor<FuncionarioEntity> funcionarioEntityArgumentCaptor =
                ArgumentCaptor.forClass(FuncionarioEntity.class);
        verify(services,times(1))
                .atualizaFuncionario(eq(1),funcionarioEntityArgumentCaptor.capture());

        assertThat(
                funcionarioEntityArgumentCaptor
                        .getValue()
                        .getNome())
                .isEqualTo("Pedro");
        // esse teste é com um caminho feliz
    }
    @Test
    public void validaParametrosAusentesAtualizaFuncionario() throws Exception,FuncionarioNaoEncontrado {
        RequisicaoDeDados dadosObtidos =
                new RequisicaoDeDados(
                        4,
                        "Pedro",
                        "Santos",
                        "Farmaceutico");

        doThrow(new FuncionarioNaoEncontrado(4))
                .when(services)
                .atualizaFuncionario(any(),any());

        mockMvc.perform(put("/Atualiza/{id_funcionario}",dadosObtidos.getId_funcionario())
                .content(objectMapper.writeValueAsString(dadosObtidos))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .string("O Funcionario com o id " + dadosObtidos.getId_funcionario() + " não foi encontrado."))
                .andReturn();

        // esse teste é com um caminho triste

    }
    @Test
    public void validaParametrosAusentesNome() throws Exception ,FuncionarioNaoEncontrado{

        RequisicaoDeDados dadosObtidos =
                new RequisicaoDeDados(
                        1,
                        null,
                        "Santos",
                        "Farmaceutico");

        mockMvc.perform(put("/Atualiza/{id_funcionario}",1)
                .content(objectMapper.writeValueAsString(dadosObtidos))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"nome\":\"Digite o campo nome.\"}"));

        verify(services,never()).atualizaFuncionario(eq(1),any(FuncionarioEntity.class));

    }

    @Test
    public void validaParametrosAusentesSobrenome() throws Exception ,FuncionarioNaoEncontrado{

        RequisicaoDeDados dadosObtidos =
                new RequisicaoDeDados(
                        1,
                        "Pedro",
                        null,
                        "Farmaceutico");

        mockMvc.perform(put("/Atualiza/{id_funcionario}",1)
                        .content(objectMapper.writeValueAsString(dadosObtidos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"sobrenome\":\"Digite o campo sobrenome.\"}"));

        verify(services,never()).atualizaFuncionario(eq(1),any(FuncionarioEntity.class));

    }
    @Test
    public void validaParametrosAusentesCargo() throws Exception ,FuncionarioNaoEncontrado{

        RequisicaoDeDados dadosObtidos =
                new RequisicaoDeDados(
                        1,
                        "Pedro",
                        "Santos",
                        null);

        mockMvc.perform(put("/Atualiza/{id_funcionario}",1)
                        .content(objectMapper.writeValueAsString(dadosObtidos))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"cargo\":\"Digite o campo cargo.\"}"));

        verify(services,never()).atualizaFuncionario(eq(1),any(FuncionarioEntity.class));

    }
}