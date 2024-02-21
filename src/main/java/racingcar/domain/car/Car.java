package racingcar.domain.car;

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
}
