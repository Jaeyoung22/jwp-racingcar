package racingcar.domain.game;

import java.time.LocalDateTime;
import java.util.List;

public class PlayResult {

    private int id;
    private final int trialCount;
    private final List<String> winners;
    private final LocalDateTime playedAt;

    public PlayResult(TrialCount trialCount, List<String> winners, LocalDateTime playedAt) {
        this.trialCount = trialCount.getValue();
        this.winners = winners;
        this.playedAt = playedAt;
    }

    public PlayResult(int id, int trialCount, List<String> winners, LocalDateTime playedAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.playedAt = playedAt;
    }

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public List<String> getWinners() {
        return winners;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }
}
