package com.mutantes.mutantesdbapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Collection para guardar cada nueva cadena de ADN analizada
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("statistics")
public class Statistic {

    @Id
    public String dnaChain;
    public Boolean isMutant;
}
