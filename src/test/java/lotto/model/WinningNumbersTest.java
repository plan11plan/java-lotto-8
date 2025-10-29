package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.util.NumberValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("숫자가 범위 내에 있으면 정상 처리")
    @Test
    void validateInRange() {
        // given
        int given = 10;

        // expect
        assertThatCode(() -> NumberValidator.validateInRange(given, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자가 경계값에 있으면 정상 처리")
    @Test
    void validateInRange_boundary() {
        // given
        int minNumber = LOTTO_MIN_NUMBER;
        int maxNumber = LOTTO_MAX_NUMBER;

        // expect
        assertThatCode(() -> NumberValidator.validateInRange(minNumber, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
        assertThatCode(() -> NumberValidator.validateInRange(maxNumber, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 숫자가 최솟값보다 작을 수 없다")
    @Test
    void validateInRange_lessThanMin_throwsException() {
        // given
        int given = 0;

        // expect
        assertThatThrownBy(() -> NumberValidator.validateInRange(given, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 숫자가 최댓값보다 클 수 없다")
    @Test
    void validateInRange_moreThanMax_throwsException() {
        // given
        int given = 46;

        // expect
        assertThatThrownBy(() -> NumberValidator.validateInRange(given, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("숫자 리스트가 범위 내에 있으면 정상 처리")
    @Test
    void validateInRange_list() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // expect
        assertThatCode(() -> NumberValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자 리스트가 경계값에 있으면 정상 처리")
    @Test
    void validateInRange_list_boundary() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 45);

        // expect
        assertThatCode(() -> NumberValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 숫자 리스트 중 하나라도 최솟값보다 작을 수 없다")
    @Test
    void validateInRange_list_lessThanMin_throwsException() {
        // given
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);

        // expect
        assertThatThrownBy(() -> NumberValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 숫자 리스트 중 하나라도 최댓값보다 클 수 없다")
    @Test
    void validateInRange_list_moreThanMax_throwsException() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 46);

        // expect
        assertThatThrownBy(() -> NumberValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("특정 단위로 나누어 떨어지면 정상 처리")
    @Test
    void validateDivisible() {
        // given
        int dividend = 10_000;
        int divisor = 1_000;

        // expect
        assertThatCode(() -> NumberValidator.validateDivisible(dividend, divisor))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 특정 단위로 나누어 떨어지지 않을 수 없다")
    @Test
    void validateDivisible_notDivisible_throwsException() {
        // given
        int dividend = 10_000;
        int divisor = 1_001;

        // expect
        assertThatThrownBy(() -> NumberValidator.validateDivisible(dividend, divisor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
