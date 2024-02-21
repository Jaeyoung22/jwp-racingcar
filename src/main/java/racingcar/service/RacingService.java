package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.car.Cars;
import racingcar.domain.car.CarsMaker;
import racingcar.domain.game.*;
import racingcar.dto.RacingInitInfo;
import racingcar.dto.RacingResult;

import java.util.List;

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
}
