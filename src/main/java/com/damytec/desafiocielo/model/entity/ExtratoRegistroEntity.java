package com.damytec.desafiocielo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class ExtratoRegistroEntity {
    @Id
    private String id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataEfetiva;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataContaCorrente;

    @Column
    private Long numeroEvento;

    @Column
    private String descGrupoPagto;

    @ManyToOne
    @JoinColumn(name = "ID_BANCO")
    private BancoEntity banco;

    @Column
    private Integer quantidadeRemessa;

    @Column
    private String cnpj;

    @Column
    private BigDecimal valor;

    @Column
    private Long numeroRemessaBanco;

    @Column
    private Character siglaSituacaoRemessa;

    @Column
    private Character siglaTipoOperacao;

    @Column
    private Integer agenciaBancaria;

    @Column
    private String contaCorrente;

    @PrePersist
    private void gerarUuid() {
        this.setId(UUID.randomUUID().toString());
    }

}
