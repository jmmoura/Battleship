package com.josiel.domain;

import java.util.Random;

public class AI extends Player {

    public AI() {
        super("AI");
    }

    public void setGridPositions() {
        Random generator = new Random();

        int submarinesToPosition = 10;
        while (submarinesToPosition > 0) {

            String spot = "";
            do {
                int index = generator.nextInt(getGrid().getAvailableSpots().size());
                spot = getGrid().getAvailableSpots().get(index);
            } while (!getGrid().isFreeSpot(spot));

            getGrid().setSubmarineSpot(spot);
            submarinesToPosition--;
        }
    }

    public void play() {
        Random generator = new Random();

        int index = generator.nextInt(getGrid().getAvailableSpots().size());
        String spot = getGrid().getAvailableSpots().get(index);

        getGrid().setShootSpot(getEnemyGrid(), spot);
    }
}
