package com.damytec.desafiocielo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtratoSumario {
    private Integer quantidadeLancamentos;
    private Integer quantidadeRemessas;
    private Double valorLancamentos;
}
