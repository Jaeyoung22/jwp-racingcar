package racingcar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Race {

    public static final String ERROR_NO_DELIMITER = "차 이름은 쉼표(,)로 구분되어야 합니다";
    public static final String ERROR_ONE_OR_NO_CAR = "고유한 차 이름이 2대 이상 있어야 합니다";

    public static Race from(String names, int count) {
        if (!names.contains(CarName.DELIMITER)) {
            throw new IllegalArgumentException(ERROR_NO_DELIMITER);
        }

        List<Car> cars = Arrays.stream(names.split(CarName.DELIMITER))
                .distinct()
                .map(CarName::from)
                .map(Car::new)
                .collect(Collectors.toList());

        if (cars.size() <= 1) {
            throw new IllegalArgumentException(ERROR_ONE_OR_NO_CAR);
        }

        TrialCount trialCount = TrialCount.from(count);

        return new Race(cars, trialCount);
    }


    private final List<Car> cars;
    private final TrialCount trialCount;
    private List<Car> winners;

    protected Race(List<Car> cars, TrialCount trialCount) {
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

    protected int pickRandomDistance() {
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
