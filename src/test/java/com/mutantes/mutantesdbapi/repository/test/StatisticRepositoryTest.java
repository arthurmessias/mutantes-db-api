package com.mutantes.mutantesdbapi.repository.test;

import com.mongodb.DuplicateKeyException;
import com.mutantes.mutantesdbapi.model.Statistic;
import com.mutantes.mutantesdbapi.repository.StatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StatisticRepository.class)
public class StatisticRepositoryTest {

    @Mock
    private StatisticRepository repository;

    @BeforeEach
    void setMockOutout() {
        when(repository.countByIsMutant(Boolean.TRUE)).thenReturn(4L);
        when(repository.countByIsMutant(Boolean.FALSE)).thenReturn(10L);
        when(repository.insert(new Statistic("AACC", false))).thenThrow(DuplicateKeyException.class);
    }

    @Test
    public void stats() {
        long mutants = repository.countByIsMutant(Boolean.TRUE);
        long nonMutants = repository.countByIsMutant(Boolean.FALSE);

        assertTrue((((double)mutants) / nonMutants) == 0.4d);
    }

    @Test
    public void duplicateKeyException() {
        assertThrows(DuplicateKeyException.class, () -> repository.insert(new Statistic("AACC", false)));
    }
}
