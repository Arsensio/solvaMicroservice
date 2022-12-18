package com.example.solva.service;

import com.example.solva.web.transaction.SaveTransactionDTO;
import com.example.solva.web.transaction.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAll();

    List<TransactionDTO> getAllExceededLimitTransactions(Long account);

    List<TransactionDTO> getByAccount(Long account,String category);

    TransactionDTO create(SaveTransactionDTO saveTransactionDTO);
}
