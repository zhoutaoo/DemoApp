package com.jlt.kata.parkingLot;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot(1);
		Car car = new Car("沪A-123");
		Receipt receipt = parkingLot.parking(car);
		parkingLot.take(receipt);
	}

}
