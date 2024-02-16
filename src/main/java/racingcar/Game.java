package racingcar;

import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Game {

    private final Cars cars;
    private final int number;

    private boolean ongoing;

    public Game(Cars cars, int number) {
        this.cars = cars;
        this.number = number;
    }

    public static Game getInstance() {
        return repeat(Game::makeGame);
    }

    public static Game makeGame() {
        String names = InputView.readNames();
        Cars cars = CarsMaker.make(names);

        int number = InputView.readNumber();

        return new Game(cars, number);
    }

    public void play() {
        repeat(this::race);
    }

    private void race() {
        if (ongoing) {
            throw new IllegalStateException("게임이 실행중입니다.");
        }

        ongoing = true;

        for (int i = 0; i < number; i++) {
            cars.race();
            Map<String, Integer> currentPositions = cars.fetchCurrentPositions();
            OutputView.printCurrentPositions(currentPositions);
        }
    }

    public void end() {
        repeat(this::quit);
    }

    private void quit() {
        if (!ongoing) {
            throw new IllegalStateException("게임이 실행되지 않았습니다.");
        }

        ongoing = false;

        List<String> winners = cars.fetchWinners();
        OutputView.printWinner(winners);
    }

    private static <T> T repeat(Supplier<T> target) {
        try {
            return target.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMsg(e);
            return repeat(target);
        }
    }

    private void repeat(Runnable target) {
        try {
            target.run();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMsg(e);
            repeat(target);
        }
    }
}
