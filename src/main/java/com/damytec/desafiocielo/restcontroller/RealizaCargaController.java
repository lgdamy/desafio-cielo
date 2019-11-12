package com.damytec.desafiocielo.restcontroller;

import com.damytec.desafiocielo.carga.CargaService;
import com.damytec.desafiocielo.model.dto.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extrato")
public class RealizaCargaController {

    @Autowired
    private CargaService service;

    @PostMapping("/carga/{quantidade}")
    public ResultData realizaCarga(@PathVariable("quantidade") Integer quantidade) {
        service.realizarCarga(quantidade);
        return new ResultData("Registros criados com sucesso");
    }
}
