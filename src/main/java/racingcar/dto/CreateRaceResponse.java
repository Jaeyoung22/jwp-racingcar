package racingcar.dto;

import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.RaceResult;

import java.util.List;
import java.util.stream.Collectors;

public class CreateRaceResponse {

    public static CreateRaceResponse from(RaceResult raceResult) {

        String winners = raceResult.getWinners()
                .stream()
                .map(Car::getName)
                .map(CarName::getValue)
                .collect(Collectors.joining(", "));

        List<RacingCarDto> racingCars = raceResult.getCars()
                .stream()
                .map(RacingCarDto::from)
                .collect(Collectors.toList());

        return new CreateRaceResponse(winners, racingCars);
    }

    private final String winners;

    private final List<RacingCarDto> racingCars;

    public CreateRaceResponse(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
