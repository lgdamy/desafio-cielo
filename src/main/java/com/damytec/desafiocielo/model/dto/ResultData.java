package com.damytec.desafiocielo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData<T,U> {
    private T sumario;
    private U detalhes;
    private Integer indice;
    private Integer tamanhoPagina;
    private Integer totalElements;
    private String mensagem;

    public ResultData(String mensagem) {
        this.mensagem = mensagem;
    }

    public ResultData(T sumario, U detalhes, Integer indice, Integer tamanhoPagina, Integer totalElements) {
        this.sumario = sumario;
        this.detalhes = detalhes;
        this.indice = indice;
        this.tamanhoPagina = tamanhoPagina;
        this.totalElements = totalElements;
    }
}
