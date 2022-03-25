package com.mutantes.mutantesdbapi.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO para persistir un documento
 */
@Data
public class StatisticRequest {
    @NotEmpty
    private List<String> dna;
    @NotNull
    private Boolean isMutant;
}
