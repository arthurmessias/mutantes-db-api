package com.mutantes.mutantesdbapi.controller;

import com.mutantes.mutantesdbapi.dto.StatisticRequest;
import com.mutantes.mutantesdbapi.dto.StatisticResponse;
import com.mutantes.mutantesdbapi.dto.StatsResponse;
import com.mutantes.mutantesdbapi.model.Statistic;
import com.mutantes.mutantesdbapi.service.IStatisticService;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Controller para obtener estadisticas
 */
@RestController
public class StatisticController {

    private final Logger logger = LogManager.getLogger(StatisticController.class);

    @Autowired
    private IStatisticService statisticService;

    @GetMapping(value = "/stats", params = "version=1")
    @Retry(name = "mutantes", fallbackMethod = "statsResponse")
    public ResponseEntity<StatsResponse> getStats() {
        StatsResponse stats = statisticService.getStatistic();

        return new ResponseEntity<StatsResponse>(stats, HttpStatus.OK);
    }

    @PostMapping(value = "/statistic", params = "version=1")
    @Retry(name = "mutantes", fallbackMethod = "statisticResponse")
    public ResponseEntity<StatisticRequest> persistStatistic(@Valid @RequestBody StatisticRequest body) {
        logger.info(body);
        Statistic statistic = statisticService.persistStatistic(body.getDna(), body.getIsMutant());

        return new ResponseEntity<StatisticRequest>(body, HttpStatus.CREATED);
    }

    @SuppressWarnings("unused")
    private ResponseEntity<StatsResponse> statsResponse(Exception ex) {
        logger.error("default response para /stats. ex msg: {}", ex.getMessage());

        return new ResponseEntity<StatsResponse>(
                new StatsResponse(0l, 0l, 0.0d),
                HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    private ResponseEntity<StatisticResponse> statisticResponse(Exception ex) {
        logger.error("default response para /statistic. ex msg: {}" + ex.getMessage());

        return new ResponseEntity<StatisticResponse>(
                new StatisticResponse(new ArrayList<String>(), Boolean.FALSE),
                HttpStatus.CREATED);
    }
}
