package com.example.solva.web.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CurrencyDTO {

    private String currencyType;
    private Double close;
    private Double previous_close;
}
