package racingcar.model;

public class Car {

    private final CarName name;

    private int position = 0;

    public Car(CarName name) {
        this.name = name;
    }

    public void move() {
        position++;
    }

    public CarName getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
