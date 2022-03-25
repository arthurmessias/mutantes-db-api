package com.mutantes.mutantesdbapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para estadisticas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {
    @JsonProperty("count_mutant_dna")
    private Long countMutant;
    @JsonProperty("count_human_dna")
    private Long countHuman;
    private Double ratio;
}
