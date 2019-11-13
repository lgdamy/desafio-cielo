package com.damytec.desafiocielo.service;


import com.damytec.desafiocielo.model.dto.ExtratoRegistro;
import com.damytec.desafiocielo.model.dto.ExtratoSumario;
import com.damytec.desafiocielo.model.dto.ResultData;
import com.damytec.desafiocielo.model.entity.BancoEntity;
import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;
import com.damytec.desafiocielo.repository.jpa.ExtratoRepository;
import com.damytec.desafiocielo.service.contract.ExtratoService;
import com.damytec.desafiocielo.service.implementation.ExtratoServiceImpl;
import com.damytec.desafiocielo.util.exception.StatusException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ExtratoServiceTest {

    @InjectMocks
    ExtratoService service = new ExtratoServiceImpl();

    @Mock
    private ExtratoRepository repository;

    private List<ExtratoRegistroEntity> registros;

    private static final Date INI = new Date(2019,11,10), FIM = new Date(2019,11,20);
    @BeforeEach
    public void setup() {
        registros = this.montarRegistros();
        Mockito.lenient().when(repository.findByDataEfetivaBetween(INI,FIM, PageRequest.of(1,2))).thenReturn(registros.stream().limit(2).collect(Collectors.toList()));
        Mockito.lenient().when(repository.findByDataEfetivaBetween(INI,FIM, Pageable.unpaged())).thenReturn(registros);
        Mockito.lenient().when(repository.findByDataEfetivaBetween(INI,FIM,PageRequest.of(2,2))).thenReturn(Arrays.asList(registros.get(2)));
        Mockito.lenient().when(repository.findByDataEfetivaBetween(INI,FIM, PageRequest.of(3,2))).thenReturn(Collections.emptyList());
    }

    @Test
    public void shouldSummarizeCorrectly() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ResultData<ExtratoSumario, List<ExtratoRegistro>> result = service.consultaExtrato(INI, FIM,2,1);
        Assertions.assertEquals(2,result.getTamanhoPagina());
        Assertions.assertEquals(1,result.getIndice());
        Assertions.assertEquals(3,result.getTotalElements());
        Assertions.assertEquals(6,result.getSumario().getQuantidadeLancamentos());
        Assertions.assertEquals(30d,result.getSumario().getValorLancamentos());
        System.out.println(">>> Pagina 1 <<<");
        System.out.println(mapper.writeValueAsString(result));

        result = service.consultaExtrato(INI, FIM,2,2);
        Assertions.assertEquals(2,result.getTamanhoPagina());
        Assertions.assertEquals(2,result.getIndice());
        Assertions.assertEquals(3,result.getTotalElements());
        Assertions.assertEquals(6,result.getSumario().getQuantidadeLancamentos());
        Assertions.assertEquals(30d,result.getSumario().getValorLancamentos());
        System.out.println(">>> Pagina 2 <<<");
        System.out.println(mapper.writeValueAsString(result));
    }

    @Test
    public void shouldThrowExceptionNoRecord() {
        Assertions.assertThrows(StatusException.class, ()
                -> service.consultaExtrato(INI,FIM,2,3));
    }



    private List<ExtratoRegistroEntity> montarRegistros() {
        BancoEntity b1 = new BancoEntity();
        b1.setCodigo(1);
        b1.setId(1);
        b1.setNome("B1");
        BancoEntity b2 = new BancoEntity();
        b2.setCodigo(1);
        b2.setId(1);
        b2.setNome("B2");

        ExtratoRegistroEntity reg1 = new ExtratoRegistroEntity();
        reg1.setValor(BigDecimal.valueOf(10));
        reg1.setQuantidadeRemessa(1);
        reg1.setBanco(b1);
        reg1.setDataEfetiva(new Date());
        reg1.setSiglaSituacaoRemessa('A');
        reg1.setSiglaTipoOperacao('B');
        ExtratoRegistroEntity reg2 = new ExtratoRegistroEntity();

        reg2.setValor(BigDecimal.valueOf(10));
        reg2.setQuantidadeRemessa(2);
        reg2.setBanco(b1);
        reg2.setDataEfetiva(new Date());
        reg2.setSiglaSituacaoRemessa('A');
        reg2.setSiglaTipoOperacao('B');
        ExtratoRegistroEntity reg3 = new ExtratoRegistroEntity();

        reg3.setValor(BigDecimal.valueOf(10));
        reg3.setQuantidadeRemessa(3);
        reg3.setBanco(b1);
        reg3.setDataEfetiva(new Date());
        reg3.setSiglaSituacaoRemessa('A');
        reg3.setSiglaTipoOperacao('B');
        return Arrays.asList(reg1,reg2,reg3);
    }
}
