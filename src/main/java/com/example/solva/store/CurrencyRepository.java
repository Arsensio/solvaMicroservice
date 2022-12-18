package com.example.solva.store;

import com.example.solva.models.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity,String> {
}
