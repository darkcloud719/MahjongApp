package com.example.mahjonggamev1.model;

public class GameRule {

    private Integer gameRuleId;
    private String gameRuleTitle;
    private Integer di;
    private Integer tai;

    public GameRule(int gameRuleId, String gameRuleTitle, int di, int tai){
        this.gameRuleId = gameRuleId;
        this.gameRuleTitle = gameRuleTitle;
        this.di = di;
        this.tai = tai;
    }

    public GameRule(int di, int tai){
        this.di = di;
        this.tai = tai;
    }

    public Integer getGameRuleId(){ return gameRuleId; }

    public String getGameRuleTitle(){ return gameRuleTitle; }

    public Integer getDi(){ return di; }

    public Integer getTai(){ return tai; }

    public void setDi(int di){ this.di = di; }

    public void setTai(int tai){ this.tai = tai; }
}
