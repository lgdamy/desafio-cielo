package com.damytec.desafiocielo.service.contract;

import com.damytec.desafiocielo.model.dto.ExtratoRegistro;
import com.damytec.desafiocielo.model.dto.ExtratoSumario;
import com.damytec.desafiocielo.model.dto.ResultData;

import java.util.Date;
import java.util.List;

public interface ExtratoService {
    ResultData<ExtratoSumario, List<ExtratoRegistro>> consultaExtrato(String ini, String end, Integer quantidadeRegistros, Integer pagina);
}
