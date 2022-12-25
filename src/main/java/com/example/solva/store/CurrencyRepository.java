package com.example.solva.store;

import com.example.solva.models.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity,String> {
}
