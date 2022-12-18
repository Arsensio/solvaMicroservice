package com.example.solva.store;

import com.example.solva.models.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    List<TransactionEntity> findAllByAccountFromAndCategory(Long accountFrom,String category);

}
