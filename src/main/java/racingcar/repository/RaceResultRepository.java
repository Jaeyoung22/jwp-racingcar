package racingcar.repository;

import racingcar.dto.RaceResultDto;

import java.util.List;

public interface RaceResultRepository {

    void save(RaceResultDto race);

}
