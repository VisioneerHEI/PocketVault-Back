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
}
