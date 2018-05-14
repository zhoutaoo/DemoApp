package com.jlt.patterns.prototype;

import com.jlt.patterns.prototype.mypackage.Graphic;

/*
 *  A concrete prototype to draw a line
 */
public class LineSymbol extends Graphic {
    public LineSymbol() {
    }

    public void DoSomething() {
        System.out.println("I am used to draw a line !");
    }
}