package com.josiel.view;

import com.josiel.domain.Player;
import com.josiel.enums.RowLabel;

import java.util.Scanner;

public class PlayerView {
    private Scanner scanner;
    private Player player;

    public PlayerView () {
        scanner = new Scanner(System.in);
    }

    protected void askName() {
        System.out.println("Informe o seu nome: ");
        String name = scanner.nextLine();

        player = new Player(name);
    }

    protected void askSubmarinePosition(int submarineNumber) {
        System.out.println((submarineNumber+1) + "° submarino.");
        String spot = askSpot();

        while (!getPlayer().getGrid().isValidSpot(spot) || !getPlayer().getGrid().isFreeSpot(spot)) {
            System.out.println("Posição inválida! Tente novamente.");
            spot = askSpot();
        }

        getPlayer().getGrid().setSubmarineSpot(spot);
    }

    public String askToPlay() {
        System.out.println("É a sua vez de jogar!");
        System.out.println("Dispare um tiro no tabuleiro do inimigo.");
        String spot = askSpot();

        while (!getPlayer().getGrid().isValidSpot(spot)) {
            System.out.println("Posição inválida! Tente novamente.");
            spot = askSpot();
        }

        boolean isAHit = getPlayer().getGrid().setShootSpot(getPlayer().getEnemyGrid(), spot);

        if (isAHit) {
            player.setHits(player.getHits()+1);
            return "AI: \"Acertou! Meu submarino está afundando!\"";
        }

        return "AI: \"Errou! O disparo foi direto para a água!\"";
    }

    public boolean askToPlayAgain() {
        System.out.println("Gostaria de jogar novamente? Se você quer, insira S, "
                + "senão, insira qualquer outra coisa: ");
        char answer = scanner.next().charAt(0);
        scanner.nextLine();
        return Character.toUpperCase(answer) == 'S';
    }

    public void printGrid() {
        System.out.println("---------------------------------------------");
        int spaces = (45 - player.getName().length()) / 2;
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.print(player.getName());
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("---------------------------------------------\n" +
                "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.print("| " + RowLabel.values()[i] + " |");
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + player.getGrid().getSpots()[i][j] + " |");
            }
        }
        System.out.println();
        System.out.println("---------------------------------------------");
    }

    private String askSpot() {
        System.out.println("Informe a posição no formato LinhaColuna (por exemplo: A5, C0...): ");
        return scanner.next().toUpperCase();
    }

    public Player getPlayer() {
        return player;
    }
}
