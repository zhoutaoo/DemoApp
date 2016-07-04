package com.jlt.game.chineseToPinyin;

public class LetterUtil {

    public static String firstUpperCase(String str) {
        if (null == str) return "";
        return firstCapitalLetter(str) + str.substring(1);
    }

    public static String firstCapitalLetter(String str) {
        return str.substring(0, 1).toUpperCase();
    }
}