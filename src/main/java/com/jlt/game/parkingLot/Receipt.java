package com.jlt.game.parkingLot;

public class Receipt {

    private String vehicleNO;
    private ParkingLot parkingLot;

    public Receipt(String vehicleNO, ParkingLot parkingLot) {
        this.vehicleNO = vehicleNO;
        this.parkingLot = parkingLot;
    }

    public ParkingLot findParkingLot() {
        return parkingLot;
    }

    public String getVehicleNO() {
        return this.vehicleNO;
    }

}
