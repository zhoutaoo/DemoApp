package com.jlt.patterns.abstractFactory;

/*
 * A concrete Wall for Living Room
 */
public class LivingRoomWall  extends Wall {
    private String wallName;
    public LivingRoomWall() {
        wallName = "LivingRoomWall";
    }
    public String getName() {
        return wallName;
    }
}