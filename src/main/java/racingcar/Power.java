package racingcar;

public class Power {

    private static final int THRESHOLD = 4;

    private final int value;

    public Power(int value) {
        this.value = value;
    }

    public boolean isEnough() {
        return value >= THRESHOLD;
    }
}
