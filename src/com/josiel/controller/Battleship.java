package com.josiel.controller;

import com.josiel.domain.AI;
import com.josiel.domain.Player;
import com.josiel.view.BattleshipView;
import com.josiel.view.PlayerView;
import com.josiel.view.ScreenUtil;

import java.util.Random;

public class Battleship {
    private Player player;
    private AI ai;
    private String winner;
    private Player playerTurn;
    private BattleshipView battleshipView = new BattleshipView();;
    private PlayerView playerView = new PlayerView();

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

    public void start() {
        boolean playerWantToPlay = true;

        while (playerWantToPlay) {

            ScreenUtil.clearScreen();
            battleshipView.showHeader();
            battleshipView.showInitialScreen();
            battleshipView.addPlayer();
            player = battleshipView.getPlayer();

            ai = new AI();
            ai.setGridPositions();

            player.setEnemyGrid(ai.getGrid());
            ai.setEnemyGrid(player.getGrid());

            Random generator = new Random();
            int index = generator.nextInt(2);

            playerTurn = index == 0 ? player : ai;

            battleshipView.setSubmarinesPositions();

            while (!isThereAWinner()) {
                if (getPlayerTurn().getClass() == AI.class) {
                    getAi().play();
                    setPlayerTurn(getPlayer());
                } else {
                    ScreenUtil.clearScreen();

                    battleshipView.showHeader();
                    battleshipView.getPlayerView().printGrid();
                    System.out.println(battleshipView.getAiResponse());
                    System.out.println();
                    battleshipView.play();
                    setPlayerTurn(getAi());
                }
            }

            ScreenUtil.clearScreen();
            battleshipView.showHeader();
            battleshipView.showWinner(winner);
            battleshipView.printGrids(player, ai);

            playerWantToPlay = battleshipView.getPlayerView().askToPlayAgain();
        }
    }
}
