package com.example.solva.models;

import com.example.solva.web.limit.LimitDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "limits_table")
public class LimitEntity {

    @EmbeddedId
    private LimitId id;

    @Column(name = "limit_setting_date")
    private String limitSettingDate;

    @Column(name = "account_limit")
    private Double accountLimit;

    @Column(name = "limit_balance")
    private Double limitBalance;



    public LimitEntity(LimitId limitId, String date, double accountLimit, double limitBalance) {
        this.id = limitId;
        this.limitSettingDate = date;
        this.accountLimit = accountLimit;
        this.limitBalance = limitBalance;
    }

    public LimitDTO toDTO() {
        return new LimitDTO(
                id.getUserAccount(),
                id.getLimitCategory(),
                accountLimit,
                limitSettingDate,
                limitBalance
        );

    }
}
