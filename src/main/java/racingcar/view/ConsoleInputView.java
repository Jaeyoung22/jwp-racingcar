package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.model.CarName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleInputView {

    private static final String NAME_DELIMITER = ",";
    private static final String PROMPT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉽표(,) 기준으로 구분)";
    private static final String PROMPT_NUMBER_OF_ATTEMPTS = "시도할 횟수는 몇 회인가요?";
    private static final String ERROR_DUPLICATE_CAR_NAME = "자동차는 서로 다른 이름을 가지고 있어야 합니다";
    private static final String ERROR_ATTEMPTS_NAN = "시도 횟수는 양의 정수여야 합니다";

    public static List<String> getCarNames() {
        List<String> carNames = null;

        while (carNames == null) {
            carNames = askCarNames();
        }

        return carNames;
    }

    private static List<String> askCarNames() {
        System.out.println(PROMPT_CAR_NAMES);
        String input = Console.readLine();

        List<String> names = Arrays.stream(input.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());

        try {
            validateCarNames(names);
            return names;
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printInputError(e);
            return null;
        }
    }

    private static void validateCarNames(List<String> names) {
        for (String name : names) {
            CarName.validateName(name);
        }

        long count = names.stream().distinct().count();
        if (names.size() != count) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_CAR_NAME);
        }
    }

    public static int getNumberOfAttempts() {
        Integer numberOfAttempts = null;

        while (numberOfAttempts == null) {
            numberOfAttempts = askNumberOfAttempts();
        }

        return numberOfAttempts;
    }

    private static Integer askNumberOfAttempts() {
        System.out.println(PROMPT_NUMBER_OF_ATTEMPTS);
        String input = Console.readLine();

        try {
            validateNumberOfAttempts(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            ConsoleOutputView.printInputError(e);
            return null;
        }
    }

    private static void validateNumberOfAttempts(String input) {
        if (!input.matches("[1-9]+")) {
            throw new IllegalArgumentException(ERROR_ATTEMPTS_NAN);
        }
    }

    private ConsoleInputView() {
    }
}
