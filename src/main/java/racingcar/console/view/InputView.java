package racingcar.console.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
    }

    public static String readNames() {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String input = Console.readLine();
            return validateNames(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMsg(e);
            return readNames();
        }
    }

    private static String validateNames(String input) {
        if (input.contains(",")) {
            return input;
        }

        throw new IllegalArgumentException("이름은 쉼표로 구분되어야 합니다.");
    }

    public static int readTrialCount() {
        try {
            System.out.println("시도할 횟수는 몇회인가요?");
            String input = Console.readLine();
            return validateNumber(input);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMsg(e);
            return readTrialCount();
        }
    }

    private static int validateNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            return validatePositive(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("양의 정수만 입력 가능합니다.");
        }
    }

    private static int validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("양의 정수만 입력 가능합니다.");
        }

        return number;
    }
}
