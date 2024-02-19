package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class RacingResultResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    private RacingResultResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }


    public static RacingResultResponse from(RacingResult result) {
        String winners = String.join(",", result.getWinners());
        Cars cars = result.getCars();

        List<CarResponse> racingCars = cars.fetchCurrentPositions()
                .entrySet()
                .stream()
                .map(entry -> CarResponse.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new RacingResultResponse(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
