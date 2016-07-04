package com.jlt.game.parkingLot;

import com.jlt.game.parkingLot.plots.ParkingPlot;

public class ParkingBuddy {
    private ParkingPlot parkingPlot;

    public ParkingBuddy(ParkingPlot parkingPlot) {
        this.parkingPlot = parkingPlot;
    }

    public Receipt parking(Car car) {
        return this.findParkingLot().parking(car);
    }

    public Car take(Receipt receipt) {
        return this.findParkingLot(receipt).take(receipt);
    }

    private ParkingLot findParkingLot() {
        return parkingPlot.findParkingLot();
    }

    private ParkingLot findParkingLot(Receipt receipt) {
        return receipt.findParkingLot();
    }
}
