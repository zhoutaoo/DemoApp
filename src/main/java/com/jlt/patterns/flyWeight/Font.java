package com.jlt.patterns.flyWeight;

/**
 *  A FlyWeight
 */
public interface Font  {
    public abstract void SetFont(String color, int size);
    public abstract void GetFont();
}