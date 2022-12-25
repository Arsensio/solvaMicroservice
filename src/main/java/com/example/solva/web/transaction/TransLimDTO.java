package com.example.solva.web.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransLimDTO  {
    private Long id;

    private String accountFrom;

    private String accountTo;

    private String currencyShortname;

    private BigDecimal sum;

    private String category;

    private String dateTime;

    private BigDecimal accountLimit;

    private String limitSettingDate;

    private boolean limitExceeded;

}
