package com.josiel.controller;

import com.josiel.domain.AI;
import com.josiel.domain.Player;

public class Battleship {
    private Player player;
    private AI ai;
    private String winner;
    private Player playerTurn;

    public Battleship (Player player) {
        this.player = player;
        ai = new AI();
        ai.setGridPositions();

        this.player.setEnemyGrid(ai.getGrid());
        ai.setEnemyGrid(this.player.getGrid());

        playerTurn = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isThereAWinner() {
        if (player.getHits() == 10) {
            winner = player.getName();
            return true;
        }

        if (ai.getHits() == 10) {
            winner = ai.getName();
            return true;
        }

        return false;
    }
}
