package com.example.mahjonggamev1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mahjonggamev1.model.GameRound;
import com.example.mahjonggamev1.model.GameRule;
import com.example.mahjonggamev1.model.Player;

import java.util.ArrayList;

public class GameViewModel extends ViewModel {

    public int gameRuleId;
    public String gameRuleTitle;
    public int di, tai, player1Income, player2Income, player3Income, player4Income;

    public  ArrayList<Player> players = new ArrayList<Player>(){{add(new Player()); add(new Player()); add(new Player()); add(new Player());}};
    public  MutableLiveData<ArrayList<Player>> mutableLiveDataPlayers = new MutableLiveData<>();
    public  MutableLiveData<GameRule> mutableLiveDataGameRule = new MutableLiveData<>();
    public  MutableLiveData<GameRound> mutableLiveDataGameRound = new MutableLiveData<>();
    public  MutableLiveData<ArrayList<GameViewModel>> mutableLiveDataGameViewModelForGameRounds = new MutableLiveData<>();
    public  MutableLiveData<ArrayList<GameViewModel>> mutableLiveDataGameViewModelForGameRules = new MutableLiveData<>();

    public LiveData<ArrayList<GameViewModel>> getMutableLiveDataGameViewModelForGameRounds(){
        return mutableLiveDataGameViewModelForGameRounds;
    }

    public LiveData<ArrayList<GameViewModel>> getMutableLiveDataGameViewModelForGameRules(){
        return mutableLiveDataGameViewModelForGameRules;
    }

    public void loadGameRules(ArrayList<GameViewModel> gameViewModelForGameRules){
        mutableLiveDataGameViewModelForGameRules.setValue(gameViewModelForGameRules);
    }

    public LiveData<ArrayList<Player>> getMutableLiveDataPlayers(){
        return mutableLiveDataPlayers;
    }

    public LiveData<GameRule> getMutableLiveDataGameRule(){
        return mutableLiveDataGameRule;
    }

    public boolean checkInitial(){
        if(players.get(0).userId != null && players.get(1).userId != null && players.get(2).userId != null && players.get(3).userId != null)
            return true;
        else
            return false;
    }

    public GameViewModel(GameRule gameRule){
        this.gameRuleId = gameRule.getGameRuleId();
        this.gameRuleTitle = gameRule.getGameRuleTitle();
        this.di = gameRule.getDi();
        this.tai = gameRule.getTai();
    }

    public GameViewModel(GameRound gameRound){
        this.player1Income = gameRound.player1Income;
        this.player2Income = gameRound.player2Income;
        this.player3Income = gameRound.player3Income;
        this.player4Income = gameRound.player4Income;
    }

    public GameViewModel(){

    }

}
