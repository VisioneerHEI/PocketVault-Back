package com.wallet.pocketvault_back.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int idUser;
    private String username;
    private String password;
    private String email;
}
