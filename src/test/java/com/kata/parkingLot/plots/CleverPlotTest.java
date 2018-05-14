package com.kata.parkingLot.plots;

import com.jlt.kata.parkingLot.ParkingLot;
import com.jlt.kata.parkingLot.UnavailableParkingLot;
import com.jlt.kata.parkingLot.plots.CleverPlot;
import com.jlt.kata.parkingLot.plots.ParkingPlot;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CleverPlotTest {

    private ParkingPlot plot;

    @Test
    public void testFindParkingLot_should_be_return_max_available_parking_spaces_number_when_he_have_some_lots() {
        ParkingLot maxParkingSpacesNumberLot = new ParkingLot(5);
        plot = new CleverPlot(new ParkingLot(2), new ParkingLot(4), maxParkingSpacesNumberLot);
        Assert.assertEquals(maxParkingSpacesNumberLot, plot.findParkingLot());
    }

    @Test
    public void testFindParkingLot_should_be_return_empty_parkingLot_when_he_have_no_available_lots() {
        plot = new CleverPlot(new ParkingLot(0), new ParkingLot(0));
        assertTrue(plot.findParkingLot() instanceof UnavailableParkingLot);
    }
}
