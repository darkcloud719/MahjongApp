package com.example.mahjonggamev1.model;

public class GameRound {
    public int bankPlayerId;
    public int bankerCount;
    public int player1Income;
    public int player2Income;
    public int player3Income;
    public int player4Income;

    public GameRound(int bankPlayerId, int bankerCount, int player1Income, int player2Income, int player3Income, int player4Income){
        this.bankPlayerId = bankPlayerId;
        this.bankerCount = bankerCount;
        this.player1Income = player1Income;
        this.player2Income = player2Income;
        this.player3Income = player3Income;
        this.player4Income = player4Income;
    }
}
