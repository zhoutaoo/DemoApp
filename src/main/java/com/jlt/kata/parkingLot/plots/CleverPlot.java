package com.jlt.kata.parkingLot.plots;

import com.game.parkingLot.ParkingLot;
import com.game.parkingLot.UnavailableParkingLot;

import java.util.Arrays;
import java.util.List;

public class CleverPlot implements ParkingPlot {
    private List<ParkingLot> parkingLots;

    public CleverPlot(ParkingLot... lots) {
        this.parkingLots = Arrays.asList(lots);
    }

    public ParkingLot findParkingLot() {
        ParkingLot availableLot = new UnavailableParkingLot();
        for (ParkingLot lot : parkingLots) {
            availableLot = availableLot.getAvailableParkingSpacesNumber() < lot.getAvailableParkingSpacesNumber() ? lot : availableLot;
        }
        return availableLot;
    }

}
