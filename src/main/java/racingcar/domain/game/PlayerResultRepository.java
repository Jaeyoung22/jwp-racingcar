package racingcar.domain.game;

import java.util.List;

public interface PlayerResultRepository {

    List<PlayerResult> saveAll(List<PlayerResult> playerResults);
}
