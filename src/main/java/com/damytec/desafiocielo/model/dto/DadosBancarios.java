package com.damytec.desafiocielo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DadosBancarios {
    private Integer codigoBanco;
    private Integer numeroAgencia;
    private String numeroContaCorrente;
}
