package racingcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class PlayResponse {
    private String winners;
    private List<CarDto> racingCars = new ArrayList<>();

    public PlayResponse(String winners) {
        this.winners = winners;
    }

    public void addCar(CarDto car) {
        racingCars.add(car);
    }
}
