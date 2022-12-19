package com.example.solva.web.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransLimDTO  {
    private Long id;

    private String accountFrom;

    private String accountTo;

    private String currencyShortname;

    private Double sum;

    private String category;

    private String dateTime;

    private Double accountLimit;

    private String limitSettingDate;

    private boolean limitExceeded;

}
