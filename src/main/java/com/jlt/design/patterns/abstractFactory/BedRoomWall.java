package com.jlt.design.patterns.abstractFactory;

/*
 * A concrete Wall for Living Room
 */
public class BedRoomWall extends Wall {
    private String wallName;
    public BedRoomWall() {
        wallName = "BedRoomWall";
    }
    public String getName() {
        return wallName;
    }
}