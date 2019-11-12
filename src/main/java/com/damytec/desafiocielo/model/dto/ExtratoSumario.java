package com.damytec.desafiocielo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtratoSumario {
    private Integer quantidadeLancamentos;
    private Integer quantidadeRemessas;
    private Double valorLancamentos;
}
