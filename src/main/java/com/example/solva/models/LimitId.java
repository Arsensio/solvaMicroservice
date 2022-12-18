package com.example.solva.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LimitId implements Serializable {

    @Column(name = "user_account")
    private Long userAccount;
    @Column(name = "limit_category")
    private String limitCategory;

}
