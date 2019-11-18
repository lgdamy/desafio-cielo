package com.damytec.desafiocielo.repository.jpa;

import com.damytec.desafiocielo.model.entity.ExtratoRegistroEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ExtratoRepository extends JpaRepository<ExtratoRegistroEntity, String> {

    List<ExtratoRegistroEntity> findByDataEfetivaBetweenOrderByDataEfetiva(Date ini, Date fim, Pageable pageable);
}
