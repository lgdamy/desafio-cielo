package com.damytec.desafiocielo.restcontroller;

import com.damytec.desafiocielo.carga.CargaService;
import com.damytec.desafiocielo.config.SwaggerConfig;
import com.damytec.desafiocielo.model.dto.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extrato")
@Api(tags = SwaggerConfig.TAG, description = SwaggerConfig.DESCRIPTION)
public class RealizaCargaController {

    @Autowired
    private CargaService service;

    @PostMapping(value = "/carga/{quantidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gera uma carga de registros pseudo-aleatórios dos ultimos 20 dias para a consulta do extrato")
    public ResultData realizaCarga(@PathVariable("quantidade") Integer quantidade) {
        service.realizarCarga(quantidade);
        return new ResultData("Registros criados com sucesso");
    }
}
