package lotto.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountValidatorTest {

    @DisplayName("특정 단위로 나누어 떨어지면 정상 처리")
    @Test
    void validateDivisible() {
        // given
        int dividend = 10_000;
        int divisor = 1_000;

        // expect
        assertThatCode(() -> AmountValidator.validateDivisible(dividend, divisor))
                .doesNotThrowAnyException();

    }

    @DisplayName("예외: 특정 단위로 나누어 떨어지지 않는다.")
    @Test
    void validateDivisible_cant_divide_throwsException() {
        // given
        int dividend = 10_000;
        int divisor = 1_001;

        // expect
        assertThatThrownBy(() -> AmountValidator.validateDivisible(dividend, divisor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
