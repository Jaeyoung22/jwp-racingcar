package racingcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import racingcar.domain.PlayResult;
import racingcar.domain.PlayerResult;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponse;
import racingcar.repository.PlayRepository;
import racingcar.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaysService {
    private final PlayRepository playRepository;
    private final PlayerRepository playerRepository;

    public List<PlayResponse> getPlays() {
        List<PlayResponse> plays = new ArrayList<>();

        for (PlayResult playResult: playRepository.getPlays()) {
            PlayResponse playResponse = new PlayResponse(playResult.getWinners());

            for (PlayerResult playerResult: playerRepository.getPlayers(playResult.getId())) {
                CarDto car = new CarDto(playerResult.getName(), playerResult.getPosition());
                playResponse.addCar(car);
            }
            plays.add(playResponse);
        }
        return plays;
    }
}
