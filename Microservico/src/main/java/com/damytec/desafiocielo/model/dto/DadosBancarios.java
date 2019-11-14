package com.damytec.desafiocielo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadosBancarios {
    private Integer codigoBanco;
    private Integer numeroAgencia;
    private String numeroContaCorrente;
}
