package com.example.solva.web.transaction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LimitExceededTransactionDTO {
    private Long id;

    private Long accountFrom;

    private Long accountTo;

    private String currencyShortname;

    private Double sum;

    private String category;

    private String dateTime;

    private Double accountLimit;

    private String limitSettingDate;

    private String limitCurrencyShortname;

    private boolean limitExceeded;
}
