package com.example.solva.controllers;

import com.example.solva.service.CurrencyService;
import com.example.solva.web.currency.CurrencyDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService apiService;

    @GetMapping
    public List<CurrencyDTO> getData(){
        return apiService.getAll();
    }

}
