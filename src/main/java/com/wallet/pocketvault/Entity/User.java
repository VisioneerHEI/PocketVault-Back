package com.wallet.pocketvault.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    private int idUser;
    private String username;
    private String password;
    private String email;
}
