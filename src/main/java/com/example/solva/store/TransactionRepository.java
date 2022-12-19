package com.example.solva.store;

import com.example.solva.models.TransactionEntity;
import com.example.solva.web.TransLimDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByAccountFromAndCategory(String accountFrom, String category);

    @Query("SELECT new com.example.solva.web.TransLimDTO(t.transactionId,t.accountFrom,t.accountTo,t.currencyShortname,t.sum,t.category,t.dateTime,l.accountLimit,l.limitSettingDate,t.limitExceeded)" +
            " FROM TransactionEntity t INNER JOIN t.limitEntity l WHERE t.limitExceeded = true AND t.accountFrom = ?1")
    List<TransLimDTO> fetchTranLimDataInnerJoin(String account);
}
