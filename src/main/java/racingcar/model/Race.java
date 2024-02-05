package racingcar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Race {

    public static Race from(String names, int count) {
        List<Car> cars = Arrays.stream(names.split(CarName.DELIMITER))
                .map(CarName::from)
                .map(Car::new)
                .collect(Collectors.toList());

        TrialCount trialCount = TrialCount.from(count);

        return new Race(cars, trialCount);
    }


    private final List<Car> cars;
    private final TrialCount trialCount;
    private List<Car> winners;

    private Race(List<Car> cars, TrialCount trialCount) {
        this.cars = cars;
        this.trialCount = trialCount;
    }

    public void play() {
        while (trialCount.decrement() > 0) {
            playTrial();
        }
        determineWinners();
    }

    private void playTrial() {
        for (Car car: cars) {
            if (pickRandomDistance() >= RaceConfig.MOVE_THRESHOLD) {
                car.move();
            }
        }
    }

    private void determineWinners() {
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

        this.winners = winners;
    }

    private int pickRandomDistance() {
        return (int)(Math.random() * 10);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getWinners() {
        return winners;
    }

    public TrialCount getTrialCount() {
        return trialCount;
    }
}
