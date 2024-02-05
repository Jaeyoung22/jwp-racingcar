package racingcar.dto;

import racingcar.model.Car;

public class CarDto {

    public static CarDto from(Car car) {
        return new CarDto(car.getName().getValue(), car.getPosition());
    }

    private final String name;

    private final int position;

    public CarDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
