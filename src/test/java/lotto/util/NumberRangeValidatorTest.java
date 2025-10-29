package lotto.util;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberRangeValidatorTest {

    @DisplayName("숫자가 범위 내에 있으면 정상 처리")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void validateInRange(int number) {
        // expect
        assertThatCode(() -> NumberRangeValidator.validateInRange(number, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
    }

    @DisplayName("경계값에서 정상 처리")
    @Test
    void validateInRange_Boundary() {
        // expect
        assertAll(
                () -> assertThatCode(() -> NumberRangeValidator.validateInRange(LOTTO_MIN_NUMBER, LOTTO_MIN_NUMBER,
                        LOTTO_MAX_NUMBER))
                        .doesNotThrowAnyException(),
                () -> assertThatCode(() -> NumberRangeValidator.validateInRange(LOTTO_MAX_NUMBER, LOTTO_MIN_NUMBER,
                        LOTTO_MAX_NUMBER))
                        .doesNotThrowAnyException()
        );
    }

    @DisplayName("예외: 숫자가 최솟값보다 작다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void validateInRange_LessThanMin_ThrowsException(int number) {
        // expect
        assertThatThrownBy(() -> NumberRangeValidator.validateInRange(number, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 숫자가 최댓값보다 크다.")
    @ParameterizedTest
    @ValueSource(ints = {46, 50, 100})
    void validateInRange_BiggerThanMax_ThrowsException(int number) {
        // expect
        assertThatThrownBy(() -> NumberRangeValidator.validateInRange(number, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
