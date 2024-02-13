package racingcar.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RaceTest {

    private static final int DISTANCE_MOVE = 4;
    private static final int DISTANCE_STOP = 1;

    @Test
    @DisplayName("예외처리: 고유한 차 이름이 1대 이하인 경우")
    void noCars() {
        String names = "john,john,john";
        int count = 2;

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Race.from(names, count))
                .withMessage(Race.ERROR_ONE_OR_NO_CAR);
    }

    @Test
    @DisplayName("예외처리: 차 이름이 쉼표로 구분되지 않은 경우")
    void noDelimiter() {
        String names = "zxcvzxczxcv";
        int count = 2;

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Race.from(names, count))
                .withMessage(Race.ERROR_NO_DELIMITER);
    }

    @Test
    @DisplayName("가장 멀리 이동한 차량이 최종 우승자로 처리되어야 한다")
    void isWinnerFound() {
        // given
        String names = "john,smith,alice";
        int count = 3;
        Race race = Race.from(names, count);
        TestRace testRace = TestRace.from(
                race,
                DISTANCE_MOVE, // john
                DISTANCE_MOVE, // smith
                DISTANCE_MOVE, // alice

                DISTANCE_STOP, // john
                DISTANCE_MOVE, // smith
                DISTANCE_STOP, // alice

                DISTANCE_STOP, // john
                DISTANCE_MOVE, // smith
                DISTANCE_STOP  // alice
                );

        // when
        testRace.play();

        // then
        List<Car> winners = testRace.getWinners();
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .map(CarName::getValue)
                .collect(Collectors.toList());
        Assertions.assertThat(winnerNames).hasSize(1).contains("smith");
    }

    static class TestRace extends Race {
        public static TestRace from(Race race, Integer ...distances) {
            return new TestRace(race.getCars(), race.getTrialCount(), Arrays.stream(distances).iterator());
        }

        private final Iterator<Integer> distances;

        private TestRace(List<Car> cars, TrialCount trialCount, Iterator<Integer> distances) {
            super(cars, trialCount);
            this.distances = distances;
        }

        @Override
        protected int pickRandomDistance() {
            return distances.next();
        }
    }

}
