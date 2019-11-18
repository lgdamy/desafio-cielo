package com.damytec.desafiocielo.restcontroller;

import com.damytec.desafiocielo.carga.CargaService;
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
@CrossOrigin
@RequestMapping("/api/extrato")
@Api(tags = SwaggerConfig.TAG,description = SwaggerConfig.DESCRIPTION)
public class ExtratosController{

    @Autowired
    private ExtratoService extratoService;

    @Autowired
    private CargaService cargaService;

    @GetMapping(value = "/consulta/{dataInicial}/{dataFinal}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Consulta os registros do extrato entre as duas datas (DDMMAAAA)")
    public ResultData consultaExtrato(
            @PathVariable("dataInicial") String ini,
            @PathVariable("dataFinal")  String fim,
            @RequestHeader(value = "tamanhoPagina", required = false, defaultValue = "10") Integer tamanhoPagina,
            @RequestHeader(value = "indice", required = false, defaultValue = "1") Integer indice
    ) {
        return extratoService.consultaExtrato(ini,fim, tamanhoPagina, indice);
    }

    @PostMapping(value = "/carga/{quantidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gera uma carga de registros pseudo-aleatórios dos ultimos 20 dias para a consulta do extrato")
    public ResultData realizaCarga(@PathVariable("quantidade") Integer quantidade) {
        cargaService.realizarCarga(quantidade);
        return new ResultData("Registros criados com sucesso");
    }
}
