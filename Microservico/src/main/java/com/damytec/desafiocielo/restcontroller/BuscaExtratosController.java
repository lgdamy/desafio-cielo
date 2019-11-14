package com.damytec.desafiocielo.restcontroller;

import com.damytec.desafiocielo.config.SwaggerConfig;
import com.damytec.desafiocielo.model.dto.ResultData;
import com.damytec.desafiocielo.service.contract.ExtratoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/extrato")
@Api(tags = SwaggerConfig.TAG,description = SwaggerConfig.DESCRIPTION)
public class BuscaExtratosController {

    @Autowired
    private ExtratoService service;

    @GetMapping(value = "/consulta/{dataInicial}/{dataFinal}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Consulta os registros do extrato entre as duas datas (DDMMAAAA)")
    public ResultData consultaExtrato(
            @PathVariable("dataInicial") String ini,
            @PathVariable("dataFinal")  String fim,
            @RequestHeader(value = "tamanhoPagina", required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestHeader(value = "indice", required = false, defaultValue = "1") Integer indice
    ) {
        return service.consultaExtrato(ini,fim, tamanhoPagina, indice);
    }

}
