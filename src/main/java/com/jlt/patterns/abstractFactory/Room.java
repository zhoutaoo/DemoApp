package com.jlt.patterns.abstractFactory;

/*
 * AbstractFactory
 */
public abstract  class Room  {
    public abstract Wall makeWall();
    public abstract Door makeDoor();
}