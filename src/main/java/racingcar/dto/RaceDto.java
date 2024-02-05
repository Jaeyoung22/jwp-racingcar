package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.Race;

import java.util.List;
import java.util.stream.Collectors;

public class RaceDto {

    public static RaceDto from(Race race) {
        int trialCount = race.getTrialCount().getValue();

        String winners = race.getWinners()
                .stream()
                .map(Car::getName)
                .map(CarName::getValue)
                .collect(Collectors.joining(", "));

        List<CarDto> racingCars = race.getCars()
                .stream()
                .map(CarDto::from)
                .collect(Collectors.toList());

        return new RaceDto(trialCount, winners, racingCars);
    }

    @JsonIgnore
    private final int trialCount;

    private final String winners;

    private final List<CarDto> racingCars;

    public RaceDto(int trialCount, String winners, List<CarDto> racingCars) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
