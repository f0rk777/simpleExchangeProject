package com.project.exchange;

import java.util.HashMap;

/**
 * Created by Alex on 19.10.2017.
 */
public class Clients {
    private String name;
    HashMap<String, Securities> hashMap;
    private int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Securities> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Securities> hashMap) {
        this.hashMap = hashMap;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
