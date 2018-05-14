package com.kata.parkingLot.plots;

import com.jlt.kata.parkingLot.Car;
import com.jlt.kata.parkingLot.ParkingLot;
import com.jlt.kata.parkingLot.UnavailableParkingLot;
import com.jlt.kata.parkingLot.plots.ParkingPlot;
import com.jlt.kata.parkingLot.plots.SmartPlot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SmartPlotTest {
    private ParkingPlot plot;

    @Test
    public void testFindParkingLot_should_be_return_max_available_parking_spaces_number_when_he_have_some_lots() {
        ParkingLot maxVacancyRateLot = new ParkingLot(3);
        maxVacancyRateLot.parking(new Car("沪C-1234"));
        ParkingLot lot1 = new ParkingLot(2);
        lot1.parking(new Car("沪B-123"));
        ParkingLot lot2 = new ParkingLot(5);
        lot2.parking(new Car("沪B-124"));
        lot2.parking(new Car("沪B-125"));
        plot = new SmartPlot(lot1, maxVacancyRateLot, lot2);
        assertEquals(maxVacancyRateLot, plot.findParkingLot());
    }

    @Test
    public void testFindParkingLot_should_be_return_empty_parkingLot_when_he_have_no_available_lots() {
        plot = new SmartPlot(new ParkingLot(0), new ParkingLot(0));
        assertTrue(plot.findParkingLot() instanceof UnavailableParkingLot);
    }
} 
