package com.wallet.pocketvault_back.Entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Currency {
    private int currencyId;
    private String currencyCode;
    private String currencyName;

    public static final String CURRENCY_ID = "currencyId";
    public static final String CURRENCY_CODE = "currencyCode";
    public static final String CURRENCY_NAME = "currencyName";
}
