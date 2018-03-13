package com.jlt.kata.guessNumeric;

public class CalculateResult {

    public Result CalculateResult(String answer, String inputString) {
        int a = countOfCorrect(answer, inputString);
        int b = countOfDigitsCorrect(answer, inputString) - a;
        return new Result(a, b);
    }

    private int countOfCorrect(String answer, String inputString) {
        int a = 0;
        for (int i = 0; i < answer.length(); i++) {
            a = a + (answer.charAt(i) == inputString.charAt(i) ? 1 : 0);
        }
        return a;
    }

    private int countOfDigitsCorrect(String answer, String inputString) {
        int b = 0;
        for (int i = 0; i < answer.length(); i++) {
            b = b + (answer.indexOf(inputString.charAt(i)) > -1 ? 1 : 0);
        }
        return b;
    }
}