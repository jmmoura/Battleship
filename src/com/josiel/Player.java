package com.josiel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private char[][] grid;
    private char[][] enemyGrid;
    private List<String> availableSpots;
    private int hits;
    private final Scanner scanner;

    public Player() {
        scanner = new Scanner(System.in);
        setGrid(new char[10][10]);
        availableSpots = new ArrayList<>();

        askName();
        setInitialGrid();
        setInitialAvailableSpots();
        setGridPositions();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public char[][] getEnemyGrid() {
        return enemyGrid;
    }

    public void setEnemyGrid(char[][] enemyGrid) {
        this.enemyGrid = enemyGrid;
    }

    public List<String> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(List<String> availableSpots) {
        this.availableSpots = availableSpots;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void play() {
        System.out.println("É a sua vez de jogar!");
        System.out.println("Dispare um tiro no tabuleiro do inimigo.");
        String spot = askSpot();

        while (!isValidSpot(spot)) {
            System.out.println("Posição inválida! Tente novamente.");
            spot = askSpot();
        }

        BattleshipApplication.clearScreen();
        System.out.println(insertSpot("Shoot", spot));
        printGrid();
    }

    public void printGrid() {
        System.out.println("---------------------------------------------");
        int spaces = (45 - name.length()) / 2;
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.print(name);
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
                System.out.print(" " + getGrid()[i][j] + " |");
            }
        }
        System.out.println();
        System.out.println("---------------------------------------------");
    }

    protected void askName() {
        System.out.println("Informe o seu nome: ");
        name = scanner.nextLine();
    }

    protected void setGridPositions() {
        System.out.println("Agora você deverá posicionar os 10 submarinos.");
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1) + "° submarino.");
            String spot = askSpot();

            while (!isValidSpot(spot) || !isFreeSpot(spot)) {
                System.out.println("Posição inválida! Tente novamente.");
                spot = askSpot();
            }

            insertSpot("Position", spot);
            BattleshipApplication.clearScreen();
            printGrid();
        }
    }

    private void setInitialAvailableSpots() {
        availableSpots = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String index = String.format("%02d", i);
            int row = Character.getNumericValue(index.charAt(0));
            char column = String.format("%2d", i).charAt(1);

            String spot = RowLabel.values()[row].name() + column;
            availableSpots.add(spot);
        }
    }

    protected boolean isValidSpot(String spot) {
        return availableSpots.contains(spot);
    }

    protected boolean isFreeSpot(String spot) {
        int row = getRowIndex(spot.charAt(0));
        int column = getColumnIndex(spot.charAt(1));

        return getGrid()[row][column] == ' ';
    }

    protected String insertSpot(String option, String spot) {
        int row = getRowIndex(spot.charAt(0));
        int column = getColumnIndex(spot.charAt(1));

        char mark = 'N';
        String message = "";
        if (option.equals("Shoot")) {
            if (isAHit(row, column)) {
                if (isFreeSpot(spot)) {
                    mark = '*';
                } else {
                    mark = 'X';
                }
                hits++;
                message = "Acertou! Meu submarino está afundando!";
            } else {
                if (isFreeSpot(spot)) {
                    mark = '-';
                } else {
                    mark = 'n';
                }
                message = "Errou! O disparo foi direto para a água!";
            }
            availableSpots.remove(spot);
        }

        getGrid()[row][column] = mark;

        return message;
    }

    private void setInitialGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                getGrid()[i][j] = ' ';
            }
        }
    }

    private int getRowIndex(char rowLabel) {
        return RowLabel.valueOf(Character.toString(rowLabel)).ordinal();
    }

    private int getColumnIndex(char columnLabel) {
        return Integer.parseInt(Character.toString(columnLabel));
    }

    private String askSpot() {
        System.out.println("Informe a posição no formato LinhaColuna (por exemplo: A5, C0...): ");
        return scanner.next().toUpperCase();
    }

    private boolean isAHit(int row, int column) {
        char enemyGridSpot = Character.toUpperCase(enemyGrid[row][column]);
        return (enemyGridSpot == 'N' || enemyGridSpot == 'X');
    }
}
