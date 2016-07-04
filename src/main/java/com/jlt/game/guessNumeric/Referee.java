package com.jlt.game.guessNumeric;

import org.apache.commons.lang.StringUtils;

public class Referee {
    private int inputStringLength = 0;

    public Referee(int inputStringLength) {
        this.inputStringLength = inputStringLength;
    }

    public String readInput(Screen screen) {
        String inputStr;
        do {
            screen.printToScreen("Please enter " + this.inputStringLength + " digits are not repeated");
            inputStr = screen.readInputString();
        } while (isInvalidInputString(inputStr));
        return inputStr;
    }

    public Result CalculateResult(String answer, String inputString) {
        CalculateResult calculateResult = new CalculateResult();
        return calculateResult.CalculateResult(answer, inputString);
    }

    public boolean isInvalidInputString(String inputStr) {
        return isNotCorrectNumeric(inputStr) || isContainsDuplicateNumeric(inputStr);
    }

    private boolean isNotCorrectNumeric(String inputStr) {
        return !StringUtils.isNumeric(inputStr) || inputStr.length() != this.inputStringLength;
    }

    private boolean isContainsDuplicateNumeric(String str) {
        boolean isContainsDuplicateNumericFlag;
        int i = 0;
        do {
            isContainsDuplicateNumericFlag = str.substring(i + 1).indexOf(str.charAt(i)) > -1;
        } while (!isContainsDuplicateNumericFlag && ++i < str.length());
        return isContainsDuplicateNumericFlag;
    }
}