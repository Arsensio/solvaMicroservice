package com.example.solva.web.limit;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LimitDTO {
    private Long account;
    private String category;
    private Double accountLimit;
    private String limitSettingDate;
    private Double limitBalance;
}
