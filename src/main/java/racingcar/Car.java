package racingcar;

import java.util.Objects;

public class Car {

    private final CarName name;

    private Position position;

    public Car(String name) {
        this.name = CarName.from(name);
        this.position = new Position(0);
    }

    public void moveIfPowerEnough(Power power) {
        if (power.isEnough()) {
            position = position.proceed();
        }
    }

    public String getName() {
        return name.getValue();
    }

    public int getPosition() {
        return position.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
