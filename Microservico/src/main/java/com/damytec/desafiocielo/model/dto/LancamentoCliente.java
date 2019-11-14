package com.damytec.desafiocielo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LancamentoCliente {
    private Long numeroRemessaBanco;
    private String nomeSituacaoRemessa;
    private DadosBancarios dadosDomiciloBancario;
    private String nomeTipoOperacao;
}
