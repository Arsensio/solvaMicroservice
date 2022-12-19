package com.example.solva.controllers;


import com.example.solva.service.TransactionService;
import com.example.solva.web.TransLimDTO;
import com.example.solva.web.transaction.SaveTransactionDTO;
import com.example.solva.web.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getAllTransaction(@RequestParam("account") String account, @RequestParam("category") String category) {
        return transactionService.getByAccount(account, category);
    }

    @PostMapping
    public TransactionDTO create(@RequestBody SaveTransactionDTO saveTransactionDTO) {
        return transactionService.create(saveTransactionDTO);
    }

    @GetMapping(value = "/limitExceeded")
    public List<TransLimDTO> getAllExceededLimitTransactions(@RequestParam("account") String account) {
        return transactionService.getAllExceededLimitTransactions(account);
    }
}
