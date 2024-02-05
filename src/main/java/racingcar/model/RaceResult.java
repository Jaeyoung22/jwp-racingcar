package racingcar.model;

import java.util.ArrayList;
import java.util.List;

public class RaceResult {

    private final List<Car> cars;

    public RaceResult(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> determineWinners() {
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
}
