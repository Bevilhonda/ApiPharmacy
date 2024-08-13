package com.farmacia.ApiFarmacia.Controller.Funcionario.Pesquisar.PeloNome;

import com.farmacia.ApiFarmacia.Model.Funcionario.Excecoes.NomeNaoEncontrado;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ByNome.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ByNomeTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ServicesFuncionario services ;

    @Test
    void getNome() throws Exception , NomeNaoEncontrado {
        FuncionarioEntity funcionario = new FuncionarioEntity
                (1, "Pedro", "Santos", "Atendente");
        FuncionarioEntity funcionario2 = new FuncionarioEntity
                (2, "Jorge", "Santos", "Atendente");
        List<FuncionarioEntity> lista = new ArrayList<>();
        lista.add(funcionario);
        lista.add(funcionario2);

        when(services.getNome("Pedro")).thenReturn(lista);
        this.mockMvc.perform(get("/Nome/Pedro"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista[0].nome")
                        .value("Pedro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lista[1].nome")
                        .value("Jorge"))
                .andReturn();

    }
    @Test
    void requisicaoPorNomeNaoCompleta() throws Exception , NomeNaoEncontrado {
        List<FuncionarioEntity> listaVazia = new ArrayList<>();

        when(services.getNome("Jorge"))
                .thenThrow( new NomeNaoEncontrado("Jorge"))
                .thenReturn(listaVazia);

        mockMvc.perform(get("/Nome/Jorge"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}