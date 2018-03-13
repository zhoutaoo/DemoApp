package com.kata.parkingLot;

import com.game.parkingLot.plots.ParkingPlot;

public class FakePlot implements ParkingPlot {
    private boolean isUse = false;

    @Override
    public ParkingLot findParkingLot() {
        this.isUse = true;
        return new ParkingLot(1);
    }

    public boolean isUse() {
        return this.isUse;
    }
}
