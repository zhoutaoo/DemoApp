package com.jlt.kata.guessNumeric;

/**
 * Created with IntelliJ IDEA.
 * User: zhoutao
 * Date: 13-12-14
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
public class GameCenter {

    public static final int ANSWER_COUNT = 4;
    public static final int MAXIMUM_NUMBER_OF_RETRIES = 10;

    private final Screen screen = new Screen();
    private final Referee referee = new Referee(ANSWER_COUNT);

    public static void main(String[] args) {
        GameCenter gameCenter = new GameCenter();
        gameCenter.init();
    }

    public void init() {
        start(new AnswerGenerate().generateAnswer(ANSWER_COUNT));
    }

    private void start(String answer) {
        this.screen.printToScreen("Game start!");
        for (int i = 0; i < MAXIMUM_NUMBER_OF_RETRIES; i++) {
            Result result = this.referee.CalculateResult(answer, this.referee.readInput(this.screen));
            this.screen.printToScreen(result.getResultMessage());
            quit(result);
        }
        this.screen.printToScreen("Game over!");
    }

    public void quit(Result result) {
        if (result.isSuccess())
            System.exit(1);
    }
}

