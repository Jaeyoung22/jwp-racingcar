package racingcar.domain.game;

import racingcar.console.view.OutputView;
import racingcar.domain.car.Cars;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {

    private final Cars cars;
    private final TrialCount trialCount;

    private boolean ongoing;

    public Game(Cars cars, TrialCount trialCount) {
        this.cars = cars;
        this.trialCount = trialCount;
    }

    public void play() {
        if (ongoing) {
            throw new IllegalStateException("게임이 실행중입니다.");
        }

        ongoing = true;

        for (int i = 0; i < trialCount.getValue(); i++) {
            cars.race();
            Map<String, Integer> currentPositions = cars.fetchPlayerResults();
            OutputView.printCurrentPositions(currentPositions);
        }
    }

    public void end() {
        if (!ongoing) {
            throw new IllegalStateException("게임이 실행되지 않았습니다.");
        }

        ongoing = false;
    }


    public PlayResult fetchPlayResult() {
        return new PlayResult(trialCount, cars.fetchWinners(), LocalDateTime.now());
    }

    public List<PlayerResult> fetchPlayerResults(PlayResult playResult) {
        return cars.fetchPlayerResults()
                .entrySet()
                .stream()
                .map(entry -> new PlayerResult(entry.getKey(), entry.getValue(), playResult.getId()))
                .collect(Collectors.toList());
    }
}
