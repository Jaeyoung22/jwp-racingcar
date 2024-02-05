package racingcar.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarNameTest {

    @Test
    @DisplayName("예외처리: 차 이름 5글자 초과")
    void tooLong() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CarName.from("qwerty"))
                .withMessage(CarName.ERROR_TOO_LONG);
    }

    @Test
    @DisplayName("예외처리: 비어 있는 차 이름")
    void empty() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> CarName.from(""))
                .withMessage(CarName.ERROR_EMPTY);
    }

}
