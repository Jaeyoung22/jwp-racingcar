package racingcar.model;

import java.util.Objects;

public class CarName {

    public static final String ERROR_EMPTY = "차 이름이 비어 있을 수 없습니다";
    public static final String ERROR_TOO_LONG = "차 이름이 5글자를 넘을 수 없습니다";
    public static final String DELIMITER = ",";

    public static CarName from(String name) {
        validateName(name);
        return new CarName(name.trim());
    }

    public static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY);
        }

        if (name.length() > 5) {
            throw new IllegalArgumentException(ERROR_TOO_LONG);
        }
    }

    private final String value;

    private CarName(String value) {
        this.value = value;
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
