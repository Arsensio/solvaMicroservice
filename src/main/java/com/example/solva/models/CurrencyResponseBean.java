package com.example.solva.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponseBean {
    @JsonProperty("values")
    private List<CurrencyEntity> values;
}
