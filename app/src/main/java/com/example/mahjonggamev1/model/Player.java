package com.example.mahjonggamev1.model;

public class Player {
    public String userId;
    public String name;
    public Integer money;
    public boolean isInitialOK = false;
    public boolean isBanker = false;
    public int bankCount = 0;

    public Player(String userId, String name, int money, boolean isBanker){
        this.userId = userId;
        this.name = name;
        this.money = money;
        this.isInitialOK = true;
        this.isBanker = isBanker;
    }

    public Player(){
        this.isInitialOK = false;
        this.isBanker = false;
        this.bankCount = 0;
    }

    public Player(String name){
        this.name = name;
        this.isInitialOK = false;
        this.isBanker = false;
        this.bankCount = 0;
    }
}
