package com.example.solva.store;

import com.example.solva.models.TransactionEntity;
import com.example.solva.web.transaction.TransLimDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByAccountFrom(String accountFrom);

    @Query("SELECT new com.example.solva.web.transaction.TransLimDTO(t.transactionId,t.accountFrom,t.accountTo,t.currencyShortname,t.sum,t.category,t.dateTime,l.accountLimit,l.limitSettingDate,t.limitExceeded)" +
            " FROM TransactionEntity t LEFT JOIN t.limitEntity  l WHERE t.limitExceeded = true AND t.accountFrom = ?1")
    List<TransLimDTO> fetchTranLimDataInnerJoin(String account);
}
