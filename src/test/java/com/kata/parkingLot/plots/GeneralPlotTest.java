package com.kata.parkingLot.plots;

import com.jlt.kata.parkingLot.ParkingLot;
import com.jlt.kata.parkingLot.UnavailableParkingLot;
import com.jlt.kata.parkingLot.plots.GeneralPlot;
import com.jlt.kata.parkingLot.plots.ParkingPlot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeneralPlotTest {
    private ParkingPlot plot;

    @Test
    public void testFindParkingLot_should_be_return_first_parkingLot_when_he_have_two_empty_lots() {
        plot = new GeneralPlot(new ParkingLot(5), new ParkingLot(10));
        assertTrue(plot.findParkingLot().isNotFull());
    }

    @Test
    public void testFindParkingLot_should_be_return_first_not_full_parkingLot_when_he_have_some_lots() {
        plot = new GeneralPlot(new ParkingLot(0), new ParkingLot(5), new ParkingLot(0));
        assertTrue(plot.findParkingLot().isNotFull());
    }

    @Test
    public void testFindParkingLot_should_be_empty_parkingLot_when_he_have_no_available_lots() {
        plot = new GeneralPlot(new ParkingLot(0), new ParkingLot(0));
        assertTrue(plot.findParkingLot() instanceof UnavailableParkingLot);
    }

} 
