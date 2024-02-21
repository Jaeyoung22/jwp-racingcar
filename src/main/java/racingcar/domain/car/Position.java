package racingcar.domain.car;

import java.util.Objects;

public class Position {

    public static final int DISTANCE_UNIT = 1;

    private final int value;

    public Position(int value) {
        this.value = value;
    }

    public Position proceed() {
        return new Position(value + DISTANCE_UNIT);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
