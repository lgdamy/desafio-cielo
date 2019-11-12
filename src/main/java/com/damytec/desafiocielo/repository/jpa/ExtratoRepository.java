package com.damytec.desafiocielo.repository.jpa;

import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ExtratoRepository extends JpaRepository<ExtratoRegistroEntity, String> {

    List<ExtratoRegistroEntity> findByDataEfetivaBetween(Date ini, Date fim, Pageable pageable);
}
