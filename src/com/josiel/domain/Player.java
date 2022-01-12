package com.josiel.domain;

import com.josiel.enums.RowLabel;
import com.josiel.view.ScreenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private Grid grid;
    private Grid enemyGrid;
    private List<String> availableSpots;
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
}
