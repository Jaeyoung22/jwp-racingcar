package racingcar.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import racingcar.repository.PlayRepository;
import racingcar.repository.PlayerRepository;

class RacingTest {

    @Mock
    PlayRepository playRepository;
    @Mock
    PlayerRepository playerRepository;

    @DisplayName("차 이름이 5자 이상인 경우 예외 처리 테스트")
    @Test
    void longCarNameExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Racing(playRepository, playerRepository).run("자동차이름1, 자동차이름2, 자동차이름3", 5);
        });
    }

    @DisplayName("차 이름이 빈 문자열인 경우 예외 처리 테스트")
    @Test
    void emptyCarNameExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new Racing(playRepository, playerRepository).run("    , 자동차1,    ", 6);
        });
    }

    @DisplayName("시도 횟수가 음수인 경우 예외 처리 테스트")
    @Test
    void tryNumExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new Racing(playRepository, playerRepository).run("자동차1, 자동차2, 자동차3", -10);
        });
    }

}