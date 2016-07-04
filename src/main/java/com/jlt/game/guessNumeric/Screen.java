package com.jlt.game.guessNumeric;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-14
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class Screen {

    public void printToScreen(String message) {
        System.out.println(message);
    }

    public String readInputString() {
        return new Scanner(System.in).nextLine();
    }
}
