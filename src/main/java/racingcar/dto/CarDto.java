package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import racingcar.model.Car;

import java.util.UUID;

public class CarDto {

    public static CarDto from(Car car) {
        return new CarDto(car.getName().getValue(), car.getPosition());
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }

    @JsonIgnore
    private final String id;

    private final String name;

    private final int position;

    public CarDto(String name, int position) {
        this.id = generateId();
        this.name = name;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
