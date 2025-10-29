package lotto.util;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberRangeValidatorTest {


    @DisplayName("단일 숫자 범위 검증")
    @Nested
    class 단일_숫자_범위_검증 {

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

    @DisplayName("숫자 리스트 범위 검증")
    @Nested
    class 숫자_리스트_범위_검증 {
        static Stream<Arguments> validRangeProvider() {
            return Stream.of(
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6)
            );
        }

        static Stream<Arguments> validBoundaryRangeProvider() {
            return Stream.of(
                    Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 45), 6)
            );
        }

        static Stream<Arguments> lessThanMinRangeProvider() {
            return Stream.of(
                    Arguments.of(Arrays.asList(-5, -4, -3, -2, -1, 0), 6)
            );
        }

        static Stream<Arguments> biggerThanMaxRangeProvider() {
            return Stream.of(
                    Arguments.of(Arrays.asList(46, 47, 48, 49, 50, 51), 6)
            );
        }

        @DisplayName("숫자가 범위 내에 있으면 정상 처리")
        @ParameterizedTest
        @MethodSource("validRangeProvider")
        void validateInRange(List<Integer> numbers) {
            // expect
            assertThatCode(() -> NumberRangeValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                    .doesNotThrowAnyException();
        }

        @DisplayName("경계값에서 정상 처리")
        @ParameterizedTest
        @MethodSource("validBoundaryRangeProvider")
        void validateInRange_Boundary(List<Integer> numbers) {
            // expect
            assertAll(
                    () -> assertThatCode(() -> NumberRangeValidator.validateInRange(numbers, LOTTO_MIN_NUMBER,
                            LOTTO_MAX_NUMBER))
                            .doesNotThrowAnyException(),
                    () -> assertThatCode(() -> NumberRangeValidator.validateInRange(numbers, LOTTO_MIN_NUMBER,
                            LOTTO_MAX_NUMBER))
                            .doesNotThrowAnyException()
            );
        }

        @DisplayName("예외: 하나라도 숫자가 최솟값보다 작으면 안된다..")
        @ParameterizedTest
        @MethodSource("lessThanMinRangeProvider")
        void validateInRange_LessThanMin_ThrowsException(List<Integer> numbers) {
            // expect
            assertThatThrownBy(() -> NumberRangeValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("예외: 하나라도 숫자가 최댓값보다 크면 안된다.")
        @ParameterizedTest
        @MethodSource("biggerThanMaxRangeProvider")
        void validateInRange_BiggerThanMax_ThrowsException(List<Integer> numbers) {
            // expect
            assertThatThrownBy(() -> NumberRangeValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

}
