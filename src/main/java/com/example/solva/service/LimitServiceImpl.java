package com.example.solva.service;

import com.example.solva.exception.ResourceNotFoundException;
import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.LimitEntity;
import com.example.solva.store.CurrencyRepository;
import com.example.solva.store.LimitRepository;
import com.example.solva.web.limit.SaveLimitDTO;
import com.example.solva.web.limit.LimitDTO;
import com.example.solva.web.limit.UpdateLimitDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public List<LimitDTO> getAll(String userAccount) {
        return limitRepository.findAllCategoryLimitsByUserAccount(userAccount).stream().map(LimitEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public LimitDTO create(SaveLimitDTO initLimitDTO) {
        return limitRepository.save(new LimitEntity(
                initLimitDTO.getAccount(),
                initLimitDTO.getCategory(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                0.0,
                0.0
        )).toDTO();
    }

    @Override
    public LimitDTO update(UpdateLimitDTO updateLimitDTO) {
        LimitEntity limitEntity = limitRepository.findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(updateLimitDTO.getAccount(), updateLimitDTO.getCategory());
        if (limitEntity != null) {
            return limitRepository.saveAndFlush(new LimitEntity(
                    limitEntity.getUserAccount(),
                    limitEntity.getLimitCategory(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                    updateLimitDTO.getAccountLimit(),
                    updateLimitDTO.getAccountLimit() - limitEntity.getAccountLimit() + limitEntity.getLimitBalance()
            )).toDTO();
        } else {
            throw new ResourceNotFoundException("There is no such Account");
        }
    }

    @Override
    public boolean updateLimitBalance(String account, String category, Double sum, String currencyShortname) {
        CurrencyEntity currentCurrency = currencyRepository.getReferenceById(currencyShortname);
        LimitEntity limitEntity = limitRepository.findFirstByUserAccountAndLimitCategoryOrderByLimitSettingDateDesc(account, category);
        if (limitEntity != null) {
            Double convertedSum = sum / currentCurrency.getClose();
            limitEntity.setLimitBalance(limitEntity.getLimitBalance() - convertedSum);
            return limitRepository.saveAndFlush(limitEntity).getLimitBalance() < 0;
        } else {
            throw new ResourceNotFoundException("There is no such Account");
        }
    }

}
