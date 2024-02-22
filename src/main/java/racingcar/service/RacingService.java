package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.car.Cars;
import racingcar.domain.car.CarsMaker;
import racingcar.domain.game.*;
import racingcar.dto.RacingInitInfo;
import racingcar.dto.RacingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RacingService {

    private final PlayResultRepository playResultRepository;
    private final PlayerResultRepository playerResultRepository;

    public RacingService(PlayResultRepository playResultRepository, PlayerResultRepository playerResultRepository) {
        this.playResultRepository = playResultRepository;
        this.playerResultRepository = playerResultRepository;
    }

    public RacingResult race(RacingInitInfo racingInitInfo) {
        String names = racingInitInfo.getNames();
        Cars cars = CarsMaker.make(names);
        TrialCount trialCount = racingInitInfo.getTrialCount();

        Game game = new Game(cars, trialCount);
        game.play();
        game.end();

        PlayResult playResult = game.fetchPlayResult();
        PlayResult savedPlayResult = playResultRepository.save(playResult);

        List<PlayerResult> playerResults = game.fetchPlayerResults(savedPlayResult);
        List<PlayerResult> savedPlayerResults = playerResultRepository.saveAll(playerResults);

        return new RacingResult(savedPlayResult, savedPlayerResults);
    }

    public List<RacingResult> readAll() {
        List<PlayResult> playResults = playResultRepository.findAll();
        List<PlayerResult> playerResults = playerResultRepository.findAll();

        List<RacingResult> results = new ArrayList<>();
        for (PlayResult playResult : playResults) {
            List<PlayerResult> playerResultsOfPlay = playerResults.stream()
                    .filter(playerResult -> playerResult.getPlayResult() == playResult.getId())
                    .collect(Collectors.toList());
            results.add(new RacingResult(playResult, playerResultsOfPlay));
        }

        return results;
    }
}
