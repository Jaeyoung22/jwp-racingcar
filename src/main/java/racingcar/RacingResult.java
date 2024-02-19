package racingcar;

import java.util.List;

public class RacingResult {

    private final List<String> winners;
    private final Cars cars;

    private RacingResult(List<String> winners, Cars cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public static RacingResult from(Cars cars) {
        List<String> winners = cars.fetchWinners();
        return new RacingResult(winners, cars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public Cars getCars() {
        return cars;
    }
}
