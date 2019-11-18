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

/**
 * Esste serviço existe apenas para popular o banco de dados com registros para a consulta, não sendo necessária a
 * implementação de testes unitários, pois ele efetivamente não pertence ao sistema.
 */
@Service
public class CargaService {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ExtratoRepository extratoRepository;

    @Transactional
    public void realizarCarga(int registros) {
        List<BancoEntity> bancos = this.gerarOuBuscarBancos();
        Random random = new Random();
        long inc = 1L;
        while (registros > 0) {
            registros--;
            ExtratoRegistroEntity registro = new ExtratoRegistroEntity();
            registro.setAgenciaBancaria(random.nextInt(10000));
            registro.setContaCorrente(random.nextInt(10000)+"");
            registro.setBanco(bancos.get(random.nextInt(bancos.size())));
            registro.setCnpj(this.gerarCnpj());
            registro.setDataContaCorrente(Date.from(LocalDate.now().minusDays(random.nextInt(20)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            registro.setDataEfetiva(Date.from(LocalDate.now().minusDays(random.nextInt(20)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            registro.setDescGrupoPagto("Gr-01");
            registro.setNumeroEvento(inc++);
            registro.setNumeroRemessaBanco(Long.valueOf(random.nextInt(1000000000)));
            registro.setSiglaSituacaoRemessa('P');
            registro.setSiglaTipoOperacao('R');
            registro.setQuantidadeRemessa(random.nextInt(20));
            registro.setValor(BigDecimal.valueOf(random.nextInt(10000)).setScale(2).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
            extratoRepository.save(registro);
        }
    }

    private List<BancoEntity> gerarOuBuscarBancos() {
        List<BancoEntity> bancos = bancoRepository.findAll();

        if (bancos.isEmpty()) {
            BancoEntity abc = new BancoEntity();
            abc.setCodigo(246);
            abc.setNome("Banco ABC");

            BancoEntity bb = new BancoEntity();
            bb.setCodigo(001);
            bb.setNome("Banco do Brasil");

            BancoEntity itau = new BancoEntity();
            itau.setCodigo(341);
            itau.setNome("Itau");

            BancoEntity snt = new BancoEntity();
            snt.setCodigo(033);
            snt.setNome("Santander");

            BancoEntity intr = new BancoEntity();
            intr.setCodigo(033);
            intr.setNome("Banco Inter");


            bancos = Arrays.asList(abc, bb, itau, snt, intr);
            bancoRepository.saveAll(bancos);
        }
        return bancos;
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
