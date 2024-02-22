package racingcar.dto;

import racingcar.domain.game.TrialCount;

public class RacingInitInfo {

    private final String names;
    private final int trialCount;

    public RacingInitInfo(String names, int trialCount) {
        this.names = names;
        this.trialCount = trialCount;
    }

    public RacingInitInfo(String names, String trialCount) {
        this.names = names;
        this.trialCount = Integer.parseInt(trialCount);
    }

    public String getNames() {
        return names;
    }

    public TrialCount getTrialCount() {
        return new TrialCount(trialCount);
    }
}
