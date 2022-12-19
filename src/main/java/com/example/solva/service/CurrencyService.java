package com.example.solva.service;

import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.CurrencyResponseBean;
import com.example.solva.store.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        Optional<CurrencyEntity> KZT = currencyRepository.findById("KZT");
        Optional<CurrencyEntity> RUB = currencyRepository.findById("RUB");
        if (KZT.isEmpty() && RUB.isEmpty()) {
            CurrencyResponseBean kzt = restTemplate.getForObject("https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&outputsize=12&apikey=7044406eb5ff4f0791cf0975ca39a5d1&source=docs", CurrencyResponseBean.class);
            CurrencyResponseBean rub = restTemplate.getForObject("https://api.twelvedata.com/time_series?symbol=USD/RUB&interval=1day&outputsize=12&apikey=7044406eb5ff4f0791cf0975ca39a5d1&source=docs", CurrencyResponseBean.class);
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


    @Scheduled(cron = "0 0 0 * * *")
    public void consumeAPI() {
        CurrencyResponseBean response = restTemplate.getForObject("https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&outputsize=12&apikey=7044406eb5ff4f0791cf0975ca39a5d1&source=docs", CurrencyResponseBean.class);
        currencyRepository.saveAndFlush(new CurrencyEntity(
                "KZT",
                response.getValues().get(0).getClose(),
                response.getValues().get(1).getClose()
        ));
    }
}
