package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.view.ConsoleInputView;
import racingcar.view.ConsoleOutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Race {
    private List<Car> cars;

    public Race() {
    }

    public void start() {
        List<String> carNames = ConsoleInputView.getCarNames();
        cars = carNames.stream().map(Car::new).collect(Collectors.toList());
        int numberOfAttempts = ConsoleInputView.getNumberOfAttempts();

        ConsoleOutputView.printResultHeader();
        while (numberOfAttempts-- > 0) {
            startRound();
        }

        RaceResult result = new RaceResult(cars);
        List<Car> winners = result.determineWinners();
        ConsoleOutputView.printWinners(winners);
    }

    private void startRound() {
        for (Car car: cars) {
            if (pickRandomDistance() >= RaceConfig.MOVE_THRESHOLD) {
                car.move();
            }
        }
        ConsoleOutputView.printRoundResult(cars);
    }

    private int pickRandomDistance() {
        return Randoms.pickNumberInRange(0, 9);
    }
}
