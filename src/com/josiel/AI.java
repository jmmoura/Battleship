package com.josiel;

import java.util.Random;

public class AI extends Player{

    @Override
    public void setGridPositions() {
        Random generator = new Random();

        int submarinesToPosition = 10;
        while (submarinesToPosition > 0) {

            String spot = "";
            do {
                int index = generator.nextInt(getAvailableSpots().size());
                spot = getAvailableSpots().get(index);
            } while (!isFreeSpot(spot));

            insertSpot("Position", spot);
            submarinesToPosition--;
        }
    }

    @Override
    public void play() {
        Random generator = new Random();

        int index = generator.nextInt(getAvailableSpots().size());
        String spot = getAvailableSpots().get(index);

        insertSpot("Shoot", spot);
    }

    @Override
    protected void askName() {
        setName("AI");
    }
}
