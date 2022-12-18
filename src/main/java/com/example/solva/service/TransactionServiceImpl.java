package com.example.solva.service;

import com.example.solva.models.TransactionEntity;
import com.example.solva.store.TransactionRepository;
import com.example.solva.web.transaction.SaveTransactionDTO;
import com.example.solva.web.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final LimitServiceImpl limitService;

    @Override
    public List<TransactionDTO> getAll() {
        return null;
    }

    @Override
    public List<TransactionDTO> getAllExceededLimitTransactions(Long account) {
        return null;
    }

    @Override
    public List<TransactionDTO> getByAccount(Long account,String category) {
        return transactionRepository.findAllByAccountFromAndCategory(account,category).stream().map(TransactionEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO create(SaveTransactionDTO saveTransactionDTO) {
        return transactionRepository.save(new TransactionEntity(
                saveTransactionDTO.getAccountFrom(),
                saveTransactionDTO.getAccountTo(),
                saveTransactionDTO.getCurrencyShortname(),
                saveTransactionDTO.getSum(),
                saveTransactionDTO.getCategory(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                limitService.updateLimitBalance(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory(), saveTransactionDTO.getSum(),saveTransactionDTO.getCurrencyShortname())
        )).toDTO();
    }
}
