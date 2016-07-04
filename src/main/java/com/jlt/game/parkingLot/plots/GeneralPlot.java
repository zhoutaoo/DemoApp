package com.jlt.game.parkingLot.plots;

import com.jlt.game.parkingLot.ParkingLot;
import com.jlt.game.parkingLot.UnavailableParkingLot;

import java.util.Arrays;
import java.util.List;

public class GeneralPlot implements ParkingPlot {
    private List<ParkingLot> parkingLots;

    public GeneralPlot(ParkingLot... lots) {
        this.parkingLots = Arrays.asList(lots);
    }

    @Override
    public ParkingLot findParkingLot() {
        ParkingLot availableLot = new UnavailableParkingLot();
        for (ParkingLot lot : this.parkingLots) {
            availableLot = lot.isNotFull() ? lot : availableLot;
        }
        return availableLot;
    }
}
