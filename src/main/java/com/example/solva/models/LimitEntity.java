package com.example.solva.models;

import com.example.solva.web.limit.LimitDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "limits_table")
public class LimitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id", nullable = false)
    private Long limitId;

    private String userAccount;

    private String limitCategory;

    @Column(name = "limit_setting_date")
    private String limitSettingDate;

    @Column(name = "account_limit")
    private BigDecimal accountLimit;

    @Column(name = "limit_balance")
    private BigDecimal limitBalance;

    @OneToMany(targetEntity = TransactionEntity.class, mappedBy = "transactionId", orphanRemoval = false, fetch = FetchType.LAZY)
    List<TransactionEntity>transactionEntityList;

    public LimitEntity(String userAccount, String limitCategory, String limitSettingDate, BigDecimal accountLimit, BigDecimal limitBalance) {
        this.userAccount = userAccount;
        this.limitCategory = limitCategory;
        this.limitSettingDate = limitSettingDate;
        this.accountLimit = accountLimit;
        this.limitBalance = limitBalance;
    }

    public LimitDTO toDTO() {
        return new LimitDTO(
                userAccount,
                limitCategory,
                accountLimit.doubleValue(),
                limitSettingDate,
                limitBalance.doubleValue()
        );

    }
}
