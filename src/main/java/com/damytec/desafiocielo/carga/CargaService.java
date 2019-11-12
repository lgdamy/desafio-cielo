package com.damytec.desafiocielo.carga;

import com.damytec.desafiocielo.model.entity.BancoEntity;
import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;
import com.damytec.desafiocielo.repository.jpa.BancoRepository;
import com.damytec.desafiocielo.repository.jpa.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CargaService {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ExtratoRepository extratoRepository;

    @Transactional
    public void realizarCarga(int registros) {
        BancoEntity abc = new BancoEntity();
        abc.setCodigo(111);
        abc.setNome("Banco ABC");

        BancoEntity bb = new BancoEntity();
        bb.setCodigo(222);
        bb.setNome("Banco do Brasil");

        BancoEntity seul = new BancoEntity();
        seul.setCodigo(333);
        seul.setNome("Banco de Seul");

        List<BancoEntity> bancos = Arrays.asList(abc,bb,seul);

        bancoRepository.saveAll(bancos);

        Random random = new Random();
        long inc = 1L;
        while (registros > 0) {
            registros--;
            ExtratoRegistroEntity registro = new ExtratoRegistroEntity();
            registro.setAgenciaBancaria(random.nextInt(10000));
            registro.setContaCorrente(random.nextInt(10000)+"");
            registro.setBanco(bancos.get(random.nextInt(3)));
            registro.setCnpj(this.gerarCnpj());
            registro.setDataContaCorrente(Date.from(LocalDate.now().plusDays(random.nextInt(20)-10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            registro.setDataEfetiva(Date.from(LocalDate.now().plusDays(random.nextInt(21)-10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            registro.setDescGrupoPagto("Gr-01");
            registro.setNumeroEvento(inc++);
            registro.setNumeroRemessaBanco(random.nextLong());
            registro.setSiglaSituacaoRemessa('P');
            registro.setSiglaTipoOperacao('R');
            registro.setQuantidadeRemessa(random.nextInt(20));
            registro.setValor(BigDecimal.valueOf(random.nextInt(10000)).setScale(2).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
            extratoRepository.save(registro);
        }
    }

    private String gerarCnpj() {
        Random r = new Random();
        String cnpj = "";
        while (cnpj.length() <= 14) {
            cnpj = cnpj + r.nextInt(10);
        }
        return cnpj;
    }
}
