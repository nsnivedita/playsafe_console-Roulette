package com.game;


public class Player {

    private String playerName;
    private String betNum;
    private double betAmount;
    private double winAmount;
    private String status;

    public Player(String playerName, String betNum, double betAmount)
    {
        this.playerName = playerName;
        this.betNum = betNum;
        this.betAmount = betAmount;
        this.status = "NULL";
        this.winAmount = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String getBetNum() {
        return betNum;
    }

    public void setBetNum(String betNum)
    {
        this.betNum = betNum;
    }
    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount)
    {
        this.betAmount = betAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setstatus(String status)
    {
        this.status = status;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount)
    {
        this.winAmount = winAmount;
    }

    public String toString(){
        return this.playerName + " " + this.betNum + " " + this.betAmount + " " + this.status + " " + this.winAmount;
    }


}
