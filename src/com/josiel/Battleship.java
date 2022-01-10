package com.josiel;

public class Battleship {
    private Player player;
    private AI ai;
    private String winner;
    private Player playerTurn;

    public Battleship () {
        player = new Player();
        ai = new AI();

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

    public void playTurn() {
        playerTurn.play();
        playerTurn = playerTurn.getClass() == ai.getClass() ? player : ai;
    }

    public void printGrids() {
        System.out.println("Tabuleiro do " + player.getName() + ": ");
        player.printGrid();

        System.out.println();
        System.out.println("Tabuleiro da AI: ");
        ai.printGrid();
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
