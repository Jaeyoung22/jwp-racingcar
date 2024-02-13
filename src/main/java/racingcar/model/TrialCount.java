package racingcar.model;

public class TrialCount {

    public static final String ERROR_NEGATIVE_TRIAL_COUNT = "시도 횟수는 양의 정수이어야 합니다";

    public static TrialCount from(int value) {
        validate(value);
        return new TrialCount(value);
    }

    private static void validate(int value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_TRIAL_COUNT);
        }
    }

    private int value;

    private TrialCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int decrement() {
        return value--;
    }
}
