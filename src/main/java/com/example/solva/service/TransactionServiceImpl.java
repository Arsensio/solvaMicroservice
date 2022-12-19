package com.example.solva.service;

import com.example.solva.models.LimitEntity;
import com.example.solva.models.TransactionEntity;
import com.example.solva.store.LimitRepository;
import com.example.solva.store.TransactionRepository;
import com.example.solva.web.TransLimDTO;
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
    private final LimitRepository limitRepository;
    private final LimitServiceImpl limitService;

    @Override
    public List<TransLimDTO> getAllExceededLimitTransactions(String account) {
        return transactionRepository.fetchTranLimDataInnerJoin(account);
    }

    @Override
    public List<TransactionDTO> getByAccount(String account) {
        return transactionRepository.findAllByAccountFrom(account).stream().map(TransactionEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO create(SaveTransactionDTO saveTransactionDTO) {
        LimitEntity limitEntity = limitRepository.findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory());
        return transactionRepository.saveAndFlush(new TransactionEntity(
                saveTransactionDTO.getAccountFrom(),
                saveTransactionDTO.getAccountTo(),
                saveTransactionDTO.getCurrencyShortname(),
                saveTransactionDTO.getSum(),
                saveTransactionDTO.getCategory(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                limitService.updateLimitBalance(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory(), saveTransactionDTO.getSum(), saveTransactionDTO.getCurrencyShortname()),
                limitRepository.findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory())
        )).toDTO();
    }
}
