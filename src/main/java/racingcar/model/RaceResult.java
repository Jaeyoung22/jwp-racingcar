package racingcar.model;

import java.util.ArrayList;
import java.util.List;

public class RaceResult {

    public static RaceResult from(List<Car> cars) {
        List<Car> winners = determineWinners(cars);
        return new RaceResult(cars, winners);
    }

    private static List<Car> determineWinners(List<Car> cars) {
        int winnerPosition = 0;
        List<Car> winners = new ArrayList<>();

        for (Car car : cars) {
            int position = car.getPosition();

            if (position > winnerPosition) {
                winnerPosition = position;
                winners.clear();
            }

            if (position == winnerPosition) {
                winners.add(car);
            }
        }

        return winners;
    }

    private final List<Car> cars;

    private final List<Car> winners;

    private RaceResult(List<Car> cars, List<Car> winners) {
        this.cars = cars;
        this.winners = winners;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getWinners() {
        return winners;
    }
}
