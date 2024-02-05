package racingcar.view;

import racingcar.model.Car;
import racingcar.model.CarName;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String HEADER_RESULT = "\n실행 결과";
    private static final String HEADER_WINNER = "최종 우승자 : ";

    public static void printInputError(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printResultHeader() {
        System.out.println(HEADER_RESULT);
    }

    public static void printRoundResult(List<Car> cars) {
        cars.forEach(ConsoleOutputView::printCarPosition);
        System.out.println();
    }

    public static void printWinners(List<Car> cars) {
        String winnerNames = cars.stream()
                .map(Car::getName)
                .map(CarName::getValue)
                .collect(Collectors.joining(", "));

        System.out.println(HEADER_WINNER + winnerNames);
    }

    private static void printCarPosition(Car car) {
        StringBuilder s = new StringBuilder();
        s.append(car.getName().getValue());
        s.append(" : ");
        for (int i = 0; i < car.getPosition(); i++) {
            s.append("-");
        }
        System.out.println(s);
    }

    private ConsoleOutputView() {}
}
