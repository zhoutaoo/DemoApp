package com.jlt.patterns.prototype;

import com.patterns.prototype.mypackage.Graphic;

/*
 *  A concrete prototype to draw a note
 */
public class NoteSymbol extends Graphic {
    public NoteSymbol() {
    }

    public void DoSomething() {
        System.out.println("I am used to draw a note !");
    }
}