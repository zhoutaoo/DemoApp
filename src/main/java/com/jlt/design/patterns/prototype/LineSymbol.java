package com.jlt.design.patterns.prototype;

import com.jlt.design.patterns.prototype.mypackage.Graphic;

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