package lotto.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionValidatorTest {

    @DisplayName("중복이 없으면 정상처리")
    @Test
    void validateDuplicateNumbers() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // expect
        assertThatCode(() -> CollectionValidator.validateDuplicateNumbers(numbers))
                .doesNotThrowAnyException();

    }

    @DisplayName("예외: 중복이 있을 수 없다.")
    @Test
    void validateDuplicateNumbers_duplicate_throwsException() {
        // given
        List<Integer> numbers = List.of(1, 1, 1, 1, 1, 2);

        // expect
        assertThatThrownBy(() -> CollectionValidator.validateDuplicateNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("특정 값과 사이즈가 같으면 정상처리")
    @Test
    void validateSize() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int size = 6;

        // expect
        assertThatCode(() -> CollectionValidator.validateSize(numbers, size))
                .doesNotThrowAnyException();

    }

    @DisplayName("예외: 특정 값과 사이즈가 다를 수 없다.")
    @Test
    void validateSize_different_throwsException() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int size = -1;

        // expect
        assertThatThrownBy(() -> CollectionValidator.validateSize(numbers, size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
