package racingcar.domain.car;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void race() {
        for (Car car : cars) {
            int value = Randoms.pickNumberInRange(1, 9);
            Power power = new Power(value);
            car.moveIfPowerEnough(power);
        }
    }

    public Map<String, Integer> fetchPlayerResults() {
        Map<String, Integer> currentPositions = new LinkedHashMap<>();

        for (Car car : cars) {
            currentPositions.put(car.getName(), car.getPosition());
        }

        return currentPositions;
    }

    public List<String> fetchWinners() {
        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
