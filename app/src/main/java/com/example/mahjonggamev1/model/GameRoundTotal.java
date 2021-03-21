package com.example.mahjonggamev1.model;

public class GameRoundTotal {
    public int bankPlayerId;
    public int bankerCount;
    public int player1TotalMoney;
    public int player2TotalMoney;
    public int player3TotalMoney;
    public int player4TotalMoney;

    public GameRoundTotal(int bankPlayerId, int bankerCount, int player1TotalMoney, int player2TotalMoney, int player3TotalMoney, int player4TotalMoney){
        this.bankPlayerId = bankPlayerId;
        this.bankerCount = bankerCount;
        this.player1TotalMoney = player1TotalMoney;
        this.player2TotalMoney = player2TotalMoney;
        this.player3TotalMoney = player3TotalMoney;
        this.player4TotalMoney = player4TotalMoney;
    }


}
