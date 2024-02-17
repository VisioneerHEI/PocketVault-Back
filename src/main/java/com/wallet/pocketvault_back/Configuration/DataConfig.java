package com.wallet.pocketvault_back.Configuration;
public class DataConfig {
    public final static String URL = System.getenv("DB_URL");
    public final static String USERNAME = System.getenv("DB_USERNAME") ;
    public final static String PASSWORD = System.getenv("DB_PASSWORD") ;

}
