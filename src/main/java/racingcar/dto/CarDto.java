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

    @JsonIgnore
    private final String raceId;

    public CarDto(String name, int position) {
        this(generateId(), name, position, null);
    }

    public CarDto(String id, String name, int position, String raceId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.raceId = raceId;
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

    public String getRaceId() {
        return raceId;
    }
}
