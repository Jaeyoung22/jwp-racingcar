package racingcar.model;

public class RaceRoundCount {

    private static final String ERROR_ROUND_NEGATIVE = "시도 횟수는 양의 정수이어야 합니다";

    public static RaceRoundCount from(int value) {
        validate(value);
        return new RaceRoundCount(value);
    }

    private static void validate(int value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException(ERROR_ROUND_NEGATIVE);
        }
    }

    private int value;

    public RaceRoundCount(int value) {
        this.value = value;
    }

    public int decrement() {
        return value--;
    }
}
