package com.damytec.desafiocielo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExtratoRegistro {
    private LancamentoCliente lancamentoContaCorrenteCLiente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataEfetivaLancamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataLancamentoContaCorrenteCliente;

    private Long numeroEvento;

    private String descricaoGrupoPagamento;

    private String codigoIdentificadorUnico;

    private String nomeBanco;

    private Integer quantidadeLancamentoRemessa;

    private String numeroRaizCNPJ;

    private String numeroSufixoCNPJ;

    private Float valorLancamentoRemessa;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date dateLancamentoContaCorrenteCliente;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date dateEfetivaLancamento;


}
