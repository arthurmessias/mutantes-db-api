package com.mutantes.mutantesdbapi.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutantes.mutantesdbapi.controller.StatisticController;
import com.mutantes.mutantesdbapi.dto.StatisticRequest;
import com.mutantes.mutantesdbapi.dto.StatsResponse;
import com.mutantes.mutantesdbapi.model.Statistic;
import com.mutantes.mutantesdbapi.service.IStatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = StatisticController.class)
public class StatisticControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStatisticService service;

    @BeforeEach
    void setMockOutput() {
        when(service.getStatistic()).thenReturn(new StatsResponse(50l,10l,5d));
    }

    @Test
    public void persistStatisticInvalidModel() throws Exception {
        StatisticRequest request = new StatisticRequest();
        request.setDna(new ArrayList<String>());
        request.setIsMutant(Boolean.TRUE);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/statistic")
                .queryParam("version","1")
                .contentType("application/json")
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void stats() throws Exception {
        this.mockMvc.perform(get("/stats")
                        .queryParam("version", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count_mutant_dna").value(50l));

    }
}
