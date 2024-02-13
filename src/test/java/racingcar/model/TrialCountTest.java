package racingcar.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrialCountTest {

    @Test
    @DisplayName("예외처리: 시도 횟수가 0 이하인 경우")
    void negative() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TrialCount.from(0))
                .withMessage(TrialCount.ERROR_NEGATIVE_TRIAL_COUNT);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> TrialCount.from(-2))
                .withMessage(TrialCount.ERROR_NEGATIVE_TRIAL_COUNT);
    }
}
