package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dto.CreateRaceRequest;
import racingcar.dto.RaceResultDto;
import racingcar.model.*;
import racingcar.repository.RaceResultRepository;

import java.util.List;

@Service
public class RaceService {

    private final RaceResultRepository raceResultRepository;

    public RaceService(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    public RaceResultDto create(CreateRaceRequest request) {
        String names = request.getNames();
        int count = request.getCount();

        Race race = Race.from(names, count);
        race.play();

        RaceResultDto raceResult = RaceResultDto.from(race);
        raceResultRepository.save(raceResult);

        return raceResult;
    }

    public List<RaceResultDto> findAll() {
        return raceResultRepository.findAll();
    }

}
