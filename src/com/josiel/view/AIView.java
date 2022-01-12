package com.josiel.view;

import com.josiel.domain.AI;
import com.josiel.enums.RowLabel;

public class AIView extends PlayerView{
    private AI ai;

    public AIView(AI ai) {
        this.ai = ai;
    }

    public void printGrid() {
        System.out.println("---------------------------------------------");
        int spaces = (45 - ai.getName().length()) / 2;
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.print(ai.getName());
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
                System.out.print(" " + ai.getGrid().getSpots()[i][j] + " |");
            }
        }
        System.out.println();
        System.out.println("---------------------------------------------");
    }
}
