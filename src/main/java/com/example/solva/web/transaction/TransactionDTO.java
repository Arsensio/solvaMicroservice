package com.example.solva.web.transaction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;

    private String accountFrom;

    private String accountTo;

    private String currencyShortname;

    private Double sum;

    private String category;

    private String dateTime;

}
