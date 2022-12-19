package com.example.solva.controllers;


import com.example.solva.service.TransactionService;
import com.example.solva.web.transaction.TransLimDTO;
import com.example.solva.web.transaction.SaveTransactionDTO;
import com.example.solva.web.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{account}")
    public List<TransactionDTO> getAllTransaction(@PathVariable String account) {
        return transactionService.getByAccount(account);
    }

    @PostMapping
    public TransactionDTO create(@RequestBody SaveTransactionDTO  saveTransactionDTO) {
        return transactionService.create(saveTransactionDTO);
    }

    @GetMapping(value = "/limitExceeded/{account}")
    public List<TransLimDTO> getAllExceededLimitTransactions(@PathVariable String account) {
        return transactionService.getAllExceededLimitTransactions(account);
    }
}
