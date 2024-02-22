package racingcar.dto;

import racingcar.domain.game.PlayResult;
import racingcar.domain.game.PlayerResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingResult {

    private final PlayResult playResult;
    private final List<PlayerResult> playerResults;

    public RacingResult(PlayResult playResult, List<PlayerResult> playerResults) {
        this.playResult = playResult;
        this.playerResults = playerResults;
    }

    public List<String> getWinners() {
        return playResult.getWinners();
    }

    public Map<String, Integer> getPlayerResults() {
        Map<String, Integer> results = new HashMap<>();
        for (PlayerResult playerResult : playerResults) {
            results.put(playerResult.getName(), playerResult.getPosition());
        }
        return results;
    }
}
