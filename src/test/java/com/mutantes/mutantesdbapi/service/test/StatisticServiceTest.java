package com.mutantes.mutantesdbapi.service.test;

import com.mutantes.mutantesdbapi.dto.StatsResponse;
import com.mutantes.mutantesdbapi.model.Statistic;
import com.mutantes.mutantesdbapi.service.IStatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = IStatisticService.class)
public class StatisticServiceTest {

    public static final List<String> DNA_LIST = Arrays.asList("AA", "BB");

    @Mock
    private IStatisticService service;

    @BeforeEach
    void setMockOutput() {
        String dnaChain = DNA_LIST.stream().collect(Collectors.joining());

        when(service.persistStatistic(DNA_LIST, Boolean.TRUE)).thenReturn(new Statistic(dnaChain, true));

        when(service.getStatistic()).thenReturn(new StatsResponse(50l,10l,5d));
    }

    @Test
    public void insertStatistic() {
        Statistic statistic = service.persistStatistic(DNA_LIST, Boolean.TRUE);

        assertTrue("AABB".equals(statistic.getDnaChain()));
    }

    @Test
    public void totalMutants() {
        StatsResponse response = service.getStatistic();

        assertTrue(response.getCountMutant() == 50l);
    }
}
