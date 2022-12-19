package com.example.solva.service;

import com.example.solva.web.limit.InitLimitDTO;
import com.example.solva.web.limit.LimitDTO;
import com.example.solva.web.limit.UpdateLimitDTO;

import java.util.List;

public interface LimitService {

    List<LimitDTO> getAll(Long userAccount);

    LimitDTO create(InitLimitDTO initLimitDTO);

    LimitDTO update(UpdateLimitDTO updateLimitDTO);

    boolean updateLimitBalance(String account, String category, Double sum, String currencyShortname);

    LimitDTO findOne(String account, String category);
}
