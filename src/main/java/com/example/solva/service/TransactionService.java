package com.example.solva.service;

import com.example.solva.web.TransLimDTO;
import com.example.solva.web.transaction.SaveTransactionDTO;
import com.example.solva.web.transaction.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransLimDTO> getAllExceededLimitTransactions(String account);

    List<TransactionDTO> getByAccount(String account);

    TransactionDTO create(SaveTransactionDTO saveTransactionDTO);
}
