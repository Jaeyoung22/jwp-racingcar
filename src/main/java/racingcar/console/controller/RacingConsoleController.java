package racingcar.console.controller;

import racingcar.console.view.InputView;
import racingcar.console.view.OutputView;
import racingcar.dto.RacingInitInfo;
import racingcar.dto.RacingResult;
import racingcar.service.RacingService;

import java.util.function.Supplier;

public class RacingConsoleController {

    private final RacingService racingService;

    public RacingConsoleController(RacingService racingService) {
        this.racingService = racingService;
    }

    public void race() {
        String names = repeat(InputView::readNames);
        int trialCount = repeat(InputView::readTrialCount);

        RacingInitInfo racingInitInfo = new RacingInitInfo(names, trialCount);
        RacingResult racingResult = racingService.race(racingInitInfo);

        OutputView.printResultNotification();
        OutputView.printWinner(racingResult.getWinners());
    }

    private <T> T repeat(Supplier<T> target) {
        try {
            return target.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMsg(e);
            return repeat(target);
        }
    }
}
