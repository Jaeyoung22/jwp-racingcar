package racingcar.dto;

import racingcar.model.Car;

public class RacingCarDto {

    public static RacingCarDto from(Car car) {
        return new RacingCarDto(car.getName().getValue(), car.getPosition());
    }

    private final String name;

    private final int position;

    public RacingCarDto(String name, int position) {
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
