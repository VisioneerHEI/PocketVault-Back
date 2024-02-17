package com.wallet.pocketvault_back.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Currency {
    @Id
    private int currencyId;
    @Column
    private String currencyCode;
    @Column
    private String currencyName;

    public static final String CURRENCY_ID = "currencyId";
    public static final String CURRENCY_CODE = "currencyCode";
    public static final String CURRENCY_NAME = "currencyName";
}
