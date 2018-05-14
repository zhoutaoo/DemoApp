package com.jlt.kata.parkingLot;

import com.jlt.kata.parkingLot.plots.ParkingPlot;

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
