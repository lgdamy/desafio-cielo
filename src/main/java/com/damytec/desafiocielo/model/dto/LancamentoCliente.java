package com.damytec.desafiocielo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LancamentoCliente {
    private Long numeroRemessaBanco;
    private String nomeSituacaoRemessa;
    private DadosBancarios dadosDomiciloBancario;
    private String nomeTipoOperacao;
}
