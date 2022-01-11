package com.josiel;

import java.util.Scanner;

public class BattleshipApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        clearScreen();

        System.out.println("Bem-vindo(a) ao Batalha Naval!\n" +
                "Você jogará contra mim, a melhor AI de Batalha Naval!.\n" +
                "Está preparado? Espero que sim!");

        boolean playerWantToPlay = true;

        while (playerWantToPlay) {
            Battleship game = new Battleship();

            while (!game.isThereAWinner()) {
                game.playTurn();
            }

            clearScreen();

            System.out.println(game.getWinner() + " venceu esta partida!");

            game.printGrids();

            System.out.println("Gostaria de jogar novamente? Se você quer, insira S. "
                    + "Senão, insira qualquer outra coisa: ");
            char answer = scanner.next().charAt(0);
            playerWantToPlay = Character.toUpperCase(answer) == 'S';
        }

        System.out.println("Obrigado por jogar Batalha Naval comigo! Até a próxima!");

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
