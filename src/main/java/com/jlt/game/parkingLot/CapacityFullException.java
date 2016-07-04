package com.jlt.game.parkingLot;

public class CapacityFullException extends RuntimeException {

    private static final String CAPACITY_IS_FULL = "车位已满!";
    private static final long serialVersionUID = 1L;

    public CapacityFullException() {
        super(CAPACITY_IS_FULL);
    }
}
