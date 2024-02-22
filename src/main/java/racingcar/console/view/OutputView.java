package racingcar.console.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String BLANK = " ";

    private OutputView() {
    }

    public static void printErrorMsg(Exception e) {
        System.out.println(ERROR_PREFIX + BLANK + e.getMessage());
    }

    public static void printResultNotification() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void printCurrentPositions(Map<String, Integer> currentPositions) {
        currentPositions.forEach((key, value) -> {
            StringBuilder positionBar = new StringBuilder();
            for (int i = 0; i < value; i++) {
                positionBar.append("-");
            }
            System.out.println(key + " : " + positionBar);
        });
    }

    public static void printWinner(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
