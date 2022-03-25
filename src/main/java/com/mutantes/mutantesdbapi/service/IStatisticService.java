package com.mutantes.mutantesdbapi.service;

import com.mutantes.mutantesdbapi.dto.StatsResponse;
import com.mutantes.mutantesdbapi.model.Statistic;

import java.util.List;

/**
 * Interfaz para StatisticService
 */
public interface IStatisticService {
    Statistic persistStatistic(List<String> dnaChain, boolean isMutant);

    StatsResponse getStatistic();
}
