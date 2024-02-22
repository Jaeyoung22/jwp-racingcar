package racingcar.domain.game;

import java.util.List;

public interface PlayResultRepository {

    PlayResult save(PlayResult playResult);

    List<PlayResult> findAll();
}
