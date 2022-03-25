package com.mutantes.mutantesdbapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO para retornar un documento
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticResponse {
    @NotEmpty
    private List<String> dna;
    @NotNull
    private Boolean isMutant;
}
