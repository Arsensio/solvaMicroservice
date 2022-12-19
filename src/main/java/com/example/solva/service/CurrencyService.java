package com.example.solva.service;

import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.CurrencyResponseBean;
import com.example.solva.store.CurrencyRepository;
import com.example.solva.web.currency.CurrencyDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;


    public List<CurrencyDTO> getAll() {
        return currencyRepository.findAll().stream().map(CurrencyEntity::toDTO).collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        CurrencyEntity KZT = currencyRepository.getReferenceById("KZT");
        if (KZT == null){
            CurrencyResponseBean response = restTemplate.getForObject("https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&outputsize=12&apikey=7044406eb5ff4f0791cf0975ca39a5d1&source=docs", CurrencyResponseBean.class);

            currencyRepository.saveAndFlush(new CurrencyEntity(
                    "KZT",
                    response.getValues().get(0).getClose(),
                    response.getValues().get(1).getClose()
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
