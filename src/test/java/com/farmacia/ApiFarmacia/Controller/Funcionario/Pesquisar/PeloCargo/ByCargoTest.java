package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloCargo;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.CargoNaoEncontrado;
import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.SobrenomeNaoEncontrado;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ByCargo.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ByCargoTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicesFuncionario services;

    @Test
    void byCargo() throws Exception, CargoNaoEncontrado {
        FuncionarioEntity funcionario = new FuncionarioEntity
                (1, "Pedro", "Santos", "Atendente");
        FuncionarioEntity funcionario2 = new FuncionarioEntity
                (2, "Jorge", "Santos", "Farmaceutico");

        List<FuncionarioEntity> lista = new ArrayList<>();
        lista.add(funcionario);
        lista.add(funcionario2);

        when(services.getCargo("Atendente")).thenReturn(lista);
        this.mockMvc.perform(get("/Cargo/Atendente"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista[0].cargo")
                        .value("Atendente"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista[1].cargo")
                        .value("Farmaceutico"))
                .andReturn();

    }

    @Test
    void requisicaoPorCargoNaoCompleta() throws Exception , CargoNaoEncontrado {
        List<FuncionarioEntity> listaVazia = new ArrayList<>();

        when(services.getCargo("Atendente"))
                .thenThrow( new CargoNaoEncontrado("Atendente"))
                .thenReturn(listaVazia);

        mockMvc.perform(get("/Cargo/Atendente"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}