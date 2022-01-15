package com.josiel.domain;

import java.util.Scanner;

public class Player {
    private String name;
    private Grid grid;
    private Grid enemyGrid;
    private int hits;
    private final Scanner scanner;

    public Player(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
        grid = new Grid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getEnemyGrid() {
        return enemyGrid;
    }

    public void setEnemyGrid(Grid enemyGrid) {
        this.enemyGrid = enemyGrid;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
