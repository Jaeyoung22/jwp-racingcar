package racingcar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import racingcar.model.Car;
import racingcar.model.CarName;
import racingcar.model.Race;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RaceResultDto {

    public static RaceResultDto from(Race race) {
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

        return new RaceResultDto(trialCount, winners, racingCars);
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }

    @JsonIgnore
    private final String id;

    @JsonIgnore
    private final int trialCount;

    private final String winners;

    private final List<CarDto> racingCars;


    private RaceResultDto(int trialCount, String winners, List<CarDto> racingCars) {
        this(generateId(), trialCount, winners, racingCars);
    }

    public RaceResultDto(String id, int trialCount, String winners, List<CarDto> racingCars) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getId() {
        return id;
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
