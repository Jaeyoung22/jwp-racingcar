package racingcar.web.dto;

import racingcar.dto.RacingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingResultResponse {

    private final List<String> winners;
    private final List<PlayerResultResponse> racingCars;

    private RacingResultResponse(List<String> winners, List<PlayerResultResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }


    public static RacingResultResponse from(RacingResult result) {
        List<String> winners = result.getWinners();
        Map<String, Integer> playerResults = result.getPlayerResults();

        List<PlayerResultResponse> racingCars = playerResults
                .entrySet()
                .stream()
                .map(entry -> PlayerResultResponse.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new RacingResultResponse(winners, racingCars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<PlayerResultResponse> getRacingCars() {
        return racingCars;
    }
}
