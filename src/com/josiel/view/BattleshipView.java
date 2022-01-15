package com.josiel.view;

import com.josiel.domain.AI;
import com.josiel.domain.Player;

public class BattleshipView {
    private PlayerView playerView = new PlayerView();
    private String aiResponse = "";
    private Player player;

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

    public void showInitialScreen() {
        System.out.println("Bem-vindo(a)!\n" +
                "Você jogará contra mim, a melhor IA de Batalha Naval!.\n" +
                "Está preparado? Espero que sim!");
    }

    public void showHeader() {
        System.out.println("BATALHA NAVAL");
        System.out.println();
    }

    public void addPlayer() {
        playerView.askName();
        setPlayer(playerView.getPlayer());
    }

    public void setSubmarinesPositions() {
        for (int i = 0; i < 10; i++) {
            ScreenUtil.clearScreen();
            showHeader();
            System.out.println("Agora você deverá posicionar os 10 submarinos.");
            playerView.printGrid();
            playerView.askSubmarinePosition(i);
        }
        playerView.printGrid();
    }

    public void play() {
        aiResponse = playerView.askToPlay();
    }

    public void showWinner(String winner) {
        System.out.println(winner + " venceu esta partida!");
    }

    public void printGrids(Player player, AI ai) {
        System.out.println("Tabuleiro do " + player.getName() + ": ");
        playerView.printGrid();

        System.out.println();
        System.out.println("Tabuleiro da AI: ");
        AIView aiView = new AIView(ai);
        aiView.printGrid();
    }
}
