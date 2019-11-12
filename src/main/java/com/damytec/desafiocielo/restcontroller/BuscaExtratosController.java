package com.damytec.desafiocielo.restcontroller;

import com.damytec.desafiocielo.model.dto.ResultData;
import com.damytec.desafiocielo.service.contract.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/extrato")
public class BuscaExtratosController {

    @Autowired
    private ExtratoService service;

    @GetMapping("/consulta/{dataInicial}/{dataFinal}")
    public ResultData consultaExtrato(
            @PathVariable("dataInicial") @DateTimeFormat(pattern = "ddMMyyyy") Date ini,
            @PathVariable("dataFinal")  @DateTimeFormat(pattern = "ddMMyyyy") Date fim,
            @RequestHeader(value = "tamanhoPagina", required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestHeader(value = "indice", required = false, defaultValue = "1") Integer indice
    ) {
        return service.consultaExtrato(ini,fim, tamanhoPagina, indice);
    }

}
