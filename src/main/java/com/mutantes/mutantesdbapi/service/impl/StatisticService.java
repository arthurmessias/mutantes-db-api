package com.mutantes.mutantesdbapi.service.impl;

import com.mutantes.mutantesdbapi.dto.StatsResponse;
import com.mutantes.mutantesdbapi.model.Statistic;
import com.mutantes.mutantesdbapi.repository.StatisticRepository;
import com.mutantes.mutantesdbapi.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase service para persistir las cadenas de ADN
 * y obtener estadisticas de los registros
 */
@Service
public class StatisticService implements IStatisticService {

    @Autowired
    private StatisticRepository repository;

    /**
     * Persiste cadena de ADN en la DB
     * @param dnaChain cadena de ADN
     * @param isMutant true MUTANTE, false NO-MUTANTE
     * @return objeto Statistc creado de manera asincronica
     */
    @Override
    public Statistic persistStatistic(List<String> dnaChain, boolean isMutant) {
        // id es la cadena de ADN
        String chainAppend = dnaChain.stream().collect(Collectors.joining());

        Statistic record = repository.save(new Statistic(chainAppend, isMutant));

        return record;
    }

    /**
     * Obtener las estadisticas de las cadenas de ADN procesadas
     * @return DTO para el servicio /stats
     */
    @Override
    public StatsResponse getStatistic() {
        Long isMutant = repository.countByIsMutant(true);
        Long nonMutant = repository.countByIsMutant(false);

        if (nonMutant == 0l)
            return new StatsResponse(isMutant, nonMutant, 0.0);

        double ratio = ((double)isMutant) / nonMutant;

        return new StatsResponse(isMutant, nonMutant, ratio);
    }
}
