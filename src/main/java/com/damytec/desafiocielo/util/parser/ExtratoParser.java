package com.damytec.desafiocielo.util.parser;

import com.damytec.desafiocielo.model.dto.*;
import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;

public class ExtratoParser {
    public static ExtratoRegistro parseEntity(ExtratoRegistroEntity entity) {
        return ExtratoRegistro.builder()
                .codigoIdentificadorUnico(entity.getId())
                .dataEfetivaLancamento(entity.getDataEfetiva())
                .dataLancamentoContaCorrenteCliente(entity.getDataContaCorrente())
                .dateEfetivaLancamento(entity.getDataEfetiva())
                .dateLancamentoContaCorrenteCliente(entity.getDataContaCorrente())
                .descricaoGrupoPagamento(entity.getDescGrupoPagto())
                .nomeBanco(entity.getBanco() != null ? entity.getBanco().getNome():null)
                .numeroEvento(entity.getNumeroEvento())
                .numeroRaizCNPJ(entity.getCnpj() != null ? entity.getCnpj().substring(0,8):null)
                .numeroSufixoCNPJ(entity.getCnpj() != null ? entity.getCnpj().substring(8):null)
                .quantidadeLancamentoRemessa(entity.getQuantidadeRemessa())
                .valorLancamentoRemessa(entity.getValor().floatValue())
                .lancamentoContaCorrenteCLiente(LancamentoCliente.builder()
                        .nomeSituacaoRemessa(SiglaSituacaoRemessa.buscaPelaSigla(entity.getSiglaSituacaoRemessa()).getDescricao())
                        .nomeTipoOperacao(SiglaTipoOperacao.buscaPelaSigla(entity.getSiglaTipoOperacao()).getDescricao())
                        .numeroRemessaBanco(entity.getNumeroRemessaBanco())
                        .dadosDomiciloBancario(DadosBancarios.builder()
                            .codigoBanco(entity.getBanco().getCodigo())
                            .numeroAgencia(entity.getAgenciaBancaria())
                            .numeroContaCorrente(entity.getContaCorrente())
                            .build())
                        .build()
                )
                .build();
    }
}
