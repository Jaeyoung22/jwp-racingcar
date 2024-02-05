package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.RaceDto;
import racingcar.model.*;

@Service
public class RaceService {

    public RaceDto create(CreateRaceRequest request) {
        String names = request.getNames();
        int count = request.getCount();

        Race race = Race.from(names, count);
        race.play();

        return RaceDto.from(race);
    }

}
