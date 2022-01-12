package com.josiel.view;

import com.josiel.controller.Battleship;
import com.josiel.domain.AI;

public class BattleshipView {
    private PlayerView playerView = new PlayerView();
    private Battleship controller;
    private String aiResponse = "";

    private void showInitialScreen() {
        System.out.println("Bem-vindo(a)!\n" +
                "Você jogará contra mim, a melhor AI de Batalha Naval!.\n" +
                "Está preparado? Espero que sim!");
    }

    private void showHeader() {
        System.out.println("BATALHA NAVAL");
        System.out.println();
    }

    private void addPlayer() {
        playerView.askName();
        controller = new Battleship(playerView.getPlayer());
    }

    private void setSubmarinesPositions() {
        for (int i = 0; i < 10; i++) {
            ScreenUtil.clearScreen();
            showHeader();
            System.out.println("Agora você deverá posicionar os 10 submarinos.");
            playerView.printGrid();
            playerView.askSubmarinePosition(i);
        }
        playerView.printGrid();
    }

    private void play() {
        aiResponse = playerView.askToPlay();
    }

    private void showWinner() {
        System.out.println(controller.getWinner() + " venceu esta partida!");
    }

    private void printGrids() {
        System.out.println("Tabuleiro do " + controller.getPlayer().getName() + ": ");
        playerView.printGrid();

        System.out.println();
        System.out.println("Tabuleiro da AI: ");
        AIView aiView = new AIView(controller.getAi());
        aiView.printGrid();
    }

    public void show() {
        boolean playerWantToPlay = true;

        while (playerWantToPlay) {

        ScreenUtil.clearScreen();
        showHeader();
        showInitialScreen();
        addPlayer();

        setSubmarinesPositions();

            while (!controller.isThereAWinner()) {
                if (controller.getPlayerTurn().getClass() == AI.class) {
                    controller.getAi().play();
                    controller.setPlayerTurn(controller.getPlayer());
                } else {
                    ScreenUtil.clearScreen();

                    showHeader();
                    playerView.printGrid();
                    System.out.println(aiResponse);
                    System.out.println();
                    play();
                    controller.setPlayerTurn(controller.getAi());
                }
            }

            ScreenUtil.clearScreen();
            showHeader();
            showWinner();
            printGrids();

            playerWantToPlay = playerView.askToPlayAgain();
        }

        System.out.println("Obrigado por jogar Batalha Naval comigo! Até a próxima!");
    }
}
