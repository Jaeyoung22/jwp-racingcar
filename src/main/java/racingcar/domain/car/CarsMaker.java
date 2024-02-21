package racingcar.domain.car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarsMaker {

    public static final String DELIMITER = ",";

    private CarsMaker() {
    }

    public static Cars make(String input) {
        String[] names = input.split(DELIMITER);
        validateDuplication(names);

        List<Car> cars = Arrays.stream(names)
                .map(String::trim)
                .map(Car::new)
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    private static void validateDuplication(String[] names) {
        long count = Arrays.stream(names)
                .distinct()
                .count();

        if (count != names.length) {
            throw new IllegalArgumentException("이름이 중복되었습니다.");
        }
    }
}
