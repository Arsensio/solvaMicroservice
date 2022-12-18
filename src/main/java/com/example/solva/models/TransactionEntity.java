package com.example.solva.models;

import com.example.solva.web.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_table")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_from")
    private Long accountFrom;

    @Column(name = "account_to")
    private Long accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortname;

    @Column(name = "sum")
    private Double sum;

    @Column(name = "expense_category")
    private String category;

    @Column(name = "datetime")
    private String dateTime;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    public TransactionEntity(Long accountFrom, Long accountTo, String currencyShortname, Double sum, String category, String dateTime, boolean limitExceeded) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.currencyShortname = currencyShortname;
        this.sum = sum;
        this.category = category;
        this.dateTime = dateTime;
        this.limitExceeded = limitExceeded;
    }

    public TransactionDTO toDTO() {
        return new TransactionDTO(
                this.id,
                this.accountFrom,
                this.accountTo,
                this.currencyShortname,
                this.sum,
                this.category,
                this.dateTime
        );
    }
}
