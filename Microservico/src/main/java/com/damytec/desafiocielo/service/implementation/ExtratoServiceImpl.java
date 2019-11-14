package com.damytec.desafiocielo.service.implementation;

import com.damytec.desafiocielo.util.exception.StatusException;
import com.damytec.desafiocielo.model.dto.ExtratoRegistro;
import com.damytec.desafiocielo.model.dto.ExtratoSumario;
import com.damytec.desafiocielo.model.dto.ResultData;
import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;
import com.damytec.desafiocielo.util.parser.ExtratoParser;
import com.damytec.desafiocielo.repository.jpa.ExtratoRepository;
import com.damytec.desafiocielo.service.contract.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtratoServiceImpl implements ExtratoService {

    @Autowired
    private ExtratoRepository extratoRepository;

    private static SimpleDateFormat SDF = new SimpleDateFormat("ddMMyyyy");

    @Override
    public ResultData<ExtratoSumario, List<ExtratoRegistro>> consultaExtrato(String iniStr, String endStr, Integer quantidadeRegistros, Integer pagina) {
        Date ini = null;
        Date end = null;
        try {
            ini = SDF.parse(iniStr);
            end = SDF.parse(endStr);
        } catch (ParseException e) {
            throw new StatusException("Formato da data deve ser DDMMAAAA", HttpStatus.BAD_REQUEST);
        }

        Pageable pg = PageRequest.of(pagina,quantidadeRegistros);
        List<ExtratoRegistroEntity> result = extratoRepository.findByDataEfetivaBetween(ini,end,pg);
        if (result.isEmpty()) {
            throw new StatusException("Nenhum registro encontrado", HttpStatus.NOT_FOUND);
        }
        List<ExtratoRegistro> registros = result.stream().map(e -> ExtratoParser.parseEntity(e)).collect(Collectors.toList());

        List<ExtratoRegistroEntity> fullData= extratoRepository.findByDataEfetivaBetween(ini,end,Pageable.unpaged());
        ExtratoSumario sumario = ExtratoSumario.builder()
                        .quantidadeLancamentos(fullData.stream().mapToInt(e -> e.getQuantidadeRemessa()).sum())
                        .quantidadeRemessas(fullData.size())
                        .valorLancamentos(fullData.stream().mapToDouble(e -> e.getValor().doubleValue()).sum())
                        .build();

        return new ResultData<>(sumario,registros,pagina,quantidadeRegistros,fullData.size(),null);

    }
}
