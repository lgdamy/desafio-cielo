package com.damytec.desafiocielo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SiglaTipoOperacao {

    REGULAR('R',"regular"),
    ESPECIAL('E',"especial"),
    IRREGULAR('I',"irregular"),
    NAO_MAPEADO(null,"nao mapeado");

    private Character sigla;
    private String descricao;

    public static SiglaTipoOperacao buscaPelaSigla(Character c) {
        return Arrays.stream(SiglaTipoOperacao.values()).
                filter(s -> c != null && c.equals(s.getSigla()))
                .findAny().orElse(SiglaTipoOperacao.NAO_MAPEADO);
    }


}
