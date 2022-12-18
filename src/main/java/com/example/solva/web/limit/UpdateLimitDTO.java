package com.example.solva.web.limit;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLimitDTO {
    private Long account;
    private String category;
    private Double accountLimit;
}
