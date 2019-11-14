package com.damytec.desafiocielo.repository.jpa;

import com.damytec.desafiocielo.model.entity.BancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<BancoEntity,Integer> {
}
