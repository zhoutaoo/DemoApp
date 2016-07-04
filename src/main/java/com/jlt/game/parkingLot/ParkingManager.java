package com.jlt.game.parkingLot;

import com.jlt.game.parkingLot.plots.ParkingPlot;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParkingManager extends ParkingBuddy {
    private List<ParkingBuddy> parkingBuddys;
    private Date now = new Date();

    public ParkingManager(Date now, ParkingPlot parkingPlot, ParkingBuddy... parkingBuddys) {
        super(parkingPlot);
        this.now = now;
        this.parkingBuddys = Arrays.asList(parkingBuddys);
    }

    @Override
    public Receipt parking(Car car) {
        if (this.isBusy()) {
            return this.findParkingBuddy().parking(car);
        }
        return super.parking(car);
    }

    @Override
    public Car take(Receipt receipt) {
        if (this.isBusy()) {
            return this.findParkingBuddy().take(receipt);
        }
        return super.take(receipt);
    }

    private ParkingBuddy findParkingBuddy() {
        return this.parkingBuddys.get(0);
    }

    private boolean isBusy() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.now);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return now.before(cal.getTime());
    }
}
