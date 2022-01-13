package com.josiel.domain;

import com.josiel.enums.RowLabel;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private char[][] spots;
    private List<String> availableSpots;

    public Grid () {
        spots = new char[10][10];
        setInitialGrid();

        availableSpots = new ArrayList<>();
        setInitialAvailableSpots();
    }

    public char[][] getSpots() {
        return spots;
    }

    public void setSpots(char[][] spots) {
        this.spots = spots;
    }

    public List<String> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(List<String> availableSpots) {
        this.availableSpots = availableSpots;
    }

    public void setSubmarineSpot(String spot) {
        int row = getRowIndex(spot.charAt(0));
        int column = getColumnIndex(spot.charAt(1));

        spots[row][column] = 'N';
    }

    public boolean setShootSpot(Grid enemyGrid, String spot) {
        int row = getRowIndex(spot.charAt(0));
        int column = getColumnIndex(spot.charAt(1));

        availableSpots.remove(spot);

        if (enemyGrid.isAHit(row, column)) {
            if (isFreeSpot(spot)) {
                spots[row][column] = '*';
            } else {
                spots[row][column] = 'X';
            }
            return true;
        }

        if (isFreeSpot(spot)) {
            spots[row][column] = '-';
        } else {
            spots[row][column] = 'n';
        }

        return false;
    }

    public boolean isValidSpot(String spot) {
        return availableSpots.contains(spot);
    }

    public boolean isFreeSpot(String spot) {
        int row = getRowIndex(spot.charAt(0));
        int column = getColumnIndex(spot.charAt(1));

        return spots[row][column] == ' ';
    }

    private void setInitialGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                spots[i][j] = ' ';
            }
        }
    }

    private void setInitialAvailableSpots() {
        for (int i = 0; i < 100; i++) {
            String index = String.format("%02d", i);
            int row = Character.getNumericValue(index.charAt(0));
            char column = String.format("%2d", i).charAt(1);

            String spot = RowLabel.values()[row].name() + column;
            availableSpots.add(spot);
        }
    }

    private boolean isAHit(int row, int column) {
        char mark = Character.toUpperCase(spots[row][column]);
        return (mark == 'N' || mark == 'X');
    }

    private int getRowIndex(char rowLabel) {
        return RowLabel.valueOf(Character.toString(rowLabel)).ordinal();
    }

    private int getColumnIndex(char columnLabel) {
        return Integer.parseInt(Character.toString(columnLabel));
    }
}
