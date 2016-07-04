package com.jlt.game.guessNumeric;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-14
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class AnswerGenerate {

    public static final int DIGITS = 10;
    public static final String BLANK_STRING = "";

    public String generateAnswer(int count) {
        String answer = "";
        while (answer.length() < count) {
            answer = answer + returnNotContainsDigitString(answer, generateDigits());
        }
        return answer;
    }

    private String returnNotContainsDigitString(String answer, char digit) {
        String digitString = String.valueOf(digit);
        return answer.contains(digitString) ? BLANK_STRING : digitString;
    }

    private char generateDigits() {
        String digitsString = String.valueOf(new Random().nextInt(DIGITS));
        return digitsString.charAt(0);
    }
}