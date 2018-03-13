package com.kata.parkingLot;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertFalse;

public class ParkingManagerTest {

    private FakePlot fakePlot;

    @Before
    public void before() {
        this.fakePlot = new FakePlot();
    }

    @Test
    public void testParking_should_be_return_a_receipt_when_he_not_busy_then_parking_a_car() throws Exception {
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-05-16 22:10:10");
        ParkingBuddy parkingManager = new ParkingManager(now, fakePlot, new ParkingBuddy(new FakePlot()));
        Car car = new Car("沪A-111");
        Receipt receipt = parkingManager.parking(car);
        assertTrue(fakePlot.isUse());
        assertNotNull(receipt.findParkingLot());
    }

    @Test
    public void testTake_should_be_return_my_car_when_he_not_busy_give_a_receipt() throws Exception {
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-05-16 12:10:10");
        ParkingBuddy parkingManager = new ParkingManager(now, fakePlot, new ParkingBuddy(new FakePlot()));
        Car myCar = new Car("沪A-111");
        Receipt receipt = parkingManager.parking(myCar);
        assertTrue(fakePlot.isUse());
        assertEquals(myCar, parkingManager.take(receipt));
    }

    @Test
    public void testParking_should_be_return_a_receipt_when_he_busy_then_parking_a_car() throws Exception {
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-05-16 11:10:10");
        ParkingBuddy parkingManager = new ParkingManager(now, fakePlot, new ParkingBuddy(new FakePlot()));
        Car car = new Car("沪A-111");
        Receipt receipt = parkingManager.parking(car);
        assertNotNull(receipt.findParkingLot());
        assertFalse(fakePlot.isUse());
    }

    @Test
    public void testTake_should_be_return_my_car_when_he_busy_give_a_receipt() throws Exception {
        Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-05-16 04:10:10");
        ParkingBuddy parkingManager = new ParkingManager(now, fakePlot, new ParkingBuddy(new FakePlot()));
        Car myCar = new Car("沪A-111");
        Receipt receipt = parkingManager.parking(myCar);
        assertFalse(fakePlot.isUse());
        assertEquals(myCar, parkingManager.take(receipt));
    }
}
