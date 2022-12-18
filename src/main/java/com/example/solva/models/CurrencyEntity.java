package com.example.solva.models;


import com.example.solva.web.currency.CurrencyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyEntity {
    @Id
    @Column(name = "currency_type", nullable = false)
    private String currencyType;

    @JsonProperty("close")
    @Column(name = "close_currency")
    private Double close;

    @Column(name = "previous_close_currency")
    private Double previous_close;

    public CurrencyDTO toDTO() {
        return new CurrencyDTO(
                this.currencyType,
                this.close,
                this.previous_close
        );
    }
}
