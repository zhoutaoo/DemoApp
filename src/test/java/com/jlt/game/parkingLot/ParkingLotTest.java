package com.jlt.game.parkingLot;

import junit.framework.Assert;

import org.junit.Test;

import static junit.framework.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Test
    public void testParking_should_be_return_receipt_when_capacity_is_not_full_give_a_car() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("沪A-123");
        // 测试执行
        Receipt receipt = parkingLot.parking(car);
        // 验证结果
        assertEquals("沪A-123", receipt.getVehicleNO());
    }

    @Test(expected = CapacityFullException.class)
    public void testParking_should_be_throw_exception_when_capacity_is_full_and_give_a_car() {
        // 准备数据
        parkingLot = new ParkingLot(1);
        Car car = new Car("沪A-111");
        // 测试执行
        parkingLot.parking(car);
        parkingLot.parking(new Car("沪A-112"));
    }

    @Test
    public void testParking_should_be_return_receipt_when_capacity_is_full_and_give_a_car_after_take_a_car() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("沪A-111");
        // 测试执行
        Receipt receipt = parkingLot.parking(car);
        parkingLot.parking(new Car("沪A-112"));
        parkingLot.take(receipt);
        // 验证结果
        parkingLot.parking(new Car("沪A-113"));
    }

    @Test
    public void testTake_should_be_return_a_correct_car_when_give_a_valid_receipt() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("沪A-123");
        // 测试执行
        Receipt receipt = parkingLot.parking(car);
        Car takeCar = parkingLot.take(receipt);
        // 验证结果
        assertEquals(car, takeCar);
    }

    @Test
    public void testTake_should_be_return_nothing_when_give_a_invalid_receipt() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("沪A-123");
        parkingLot.parking(car);
        Receipt invalidReceipt = new Receipt("沪A-123", parkingLot);
        // 测试执行
        Car takeCar = parkingLot.take(invalidReceipt);
        // 验证结果
        assertNull(takeCar);
    }

    @Test
    public void testCalculateVacancyRate_should_be_return_vacancy_rate_when_parking() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parking(new Car("沪A-123"));
        // 测试执行
        // 验证结果
        assertEquals(0.5f, parkingLot.calculateVacancyRate());
    }

    @Test
    public void testIsFull_should_be_return_true_when_have_no_available_space() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.parking(new Car("沪A-123"));
        // 测试执行
        // 验证结果
        assertTrue(parkingLot.isFull());
    }

    @Test
    public void testIsNotFull_should_be_return_true_when_available_space() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.parking(new Car("沪A-123"));
        // 测试执行
        // 验证结果
        assertTrue(parkingLot.isNotFull());
    }

    @Test
    public void testGetAvailableParkingSpacesNumber_should_be_return_available_parking_spaces_number() {
        // 准备数据
        ParkingLot parkingLot = new ParkingLot(5);
        // 测试执行
        parkingLot.parking(new Car("沪A-123"));
        parkingLot.parking(new Car("沪B-123"));
        // 验证结果
        assertEquals(3, parkingLot.getAvailableParkingSpacesNumber());
    }

}
