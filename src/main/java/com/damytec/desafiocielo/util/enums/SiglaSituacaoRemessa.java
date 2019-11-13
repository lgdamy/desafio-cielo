package com.damytec.desafiocielo.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SiglaSituacaoRemessa {

    PAGO('P',"Pago"),
    NAO_PAGO('N',"Nao Pago"),
    NAO_MAPEADO(null,"Nao Mapeado");

    private Character sigla;
    private String descricao;

    public static SiglaSituacaoRemessa buscaPelaSigla(Character c) {
        return Arrays.stream(SiglaSituacaoRemessa.values()).
                filter(s -> c != null && c.equals(s.getSigla()))
                .findAny().orElse(SiglaSituacaoRemessa.NAO_MAPEADO);
    }


}
