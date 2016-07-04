package com.jlt.game.parkingLot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private Map<Receipt, Car> parkingSpaces = new ConcurrentHashMap();
    private int parkCapacity;

    public ParkingLot(int parkCapacity) {
        this.parkCapacity = parkCapacity;
    }

    public Receipt parking(Car car) {
        if (this.isFull())
            throw new CapacityFullException();
        Receipt receipt = new Receipt(car.getVehicleNO(), this);
        this.parkingSpaces.put(receipt, car);
        return receipt;
    }

    public Car take(Receipt receipt) {
        return this.parkingSpaces.remove(receipt);
    }

    public boolean isFull() {
        return this.parkingSpaces.size() >= this.parkCapacity;
    }

    public boolean isNotFull() {
        return !this.isFull();
    }

    public int getAvailableParkingSpacesNumber() {
        return this.parkCapacity - this.parkingSpaces.size();
    }

    public float calculateVacancyRate() {
        if (this.parkCapacity == 0)
            return 0.0f;
        return 1 - (float) this.parkingSpaces.size() / this.parkCapacity;
    }
}
