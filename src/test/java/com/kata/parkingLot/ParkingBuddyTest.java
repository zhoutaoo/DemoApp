package com.kata.parkingLot;

import com.jlt.kata.parkingLot.Car;
import com.jlt.kata.parkingLot.ParkingBuddy;
import com.jlt.kata.parkingLot.Receipt;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ParkingBuddyTest {

    @Test
    public void testParking_should_be_return_a_receipt_when_parking_a_car() throws Exception {
        ParkingBuddy parkingBuddy = new ParkingBuddy(new FakePlot());
        Car car = new Car("沪A-111");
        Receipt receipt = parkingBuddy.parking(car);
        assertNotNull(receipt.findParkingLot());
    }

    @Test
    public void testTake_should_be_return_my_car_when_give_a_receipt() throws Exception {
        ParkingBuddy parkingBuddy = new ParkingBuddy(new FakePlot());
        Car myCar = new Car("沪A-111");
        Receipt receipt = parkingBuddy.parking(myCar);
        assertEquals(myCar, parkingBuddy.take(receipt));
    }
}
