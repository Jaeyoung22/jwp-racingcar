package racingcar;

import java.util.Objects;

public class CarName {

    private final String value;

    private CarName(String value) {
        this.value = value;
    }

    public static CarName from(String name) {
        validate(name);
        return new CarName(name);
    }

    private static void validate(String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarName carName = (CarName) o;
        return Objects.equals(value, carName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
