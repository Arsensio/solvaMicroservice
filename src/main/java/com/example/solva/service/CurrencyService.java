package com.example.solva.service;

import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.CurrencyResponseBean;
import com.example.solva.store.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyService(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
    }

    @Value("${api.kzt}")
    private String kztApi;

    @Value("${api.rub}")
    private String rubApi;


    @PostConstruct
    public void init() {
        Optional<CurrencyEntity> KZT = currencyRepository.findById("KZT");
        Optional<CurrencyEntity> RUB = currencyRepository.findById("RUB");
        if (KZT.isEmpty() && RUB.isEmpty()) {
            getCurrency();
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void setCurrency() {
        getCurrency();
    }

    private void getCurrency() {
        CurrencyResponseBean kzt = restTemplate.getForObject(kztApi, CurrencyResponseBean.class);
        CurrencyResponseBean rub = restTemplate.getForObject(rubApi, CurrencyResponseBean.class);
        currencyRepository.saveAndFlush(new CurrencyEntity(
                "KZT",
                kzt.getValues().get(0).getClose(),
                kzt.getValues().get(1).getClose()
        ));
        currencyRepository.saveAndFlush(new CurrencyEntity(
                "RUB",
                rub.getValues().get(0).getClose(),
                rub.getValues().get(1).getClose()
        ));
    }
}
