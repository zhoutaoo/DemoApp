package com.jlt.game.guessNumeric;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-13
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
class Result {
    public static final String SUCCESS_CODE = "4A0B";
    private String resultCode;

    public Result(int a, int b) {
        this.resultCode = a + "A" + b + "B";
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(resultCode);
    }

    public String getResultMessage() {
        return this.resultCode + "," + (this.isSuccess() ? "Guess Success" : "Fail");
    }
}
