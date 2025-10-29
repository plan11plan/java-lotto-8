package lotto.util;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionValidatorTest {

    @DisplayName("중복이 없으면 정상처리")
    @Test
    void validateDuplicateNumbers() {
        // given
        List<Integer> numbers = List.of(1, 1, 1, 1, 1, 2);

        // expect
        Assertions.assertThatThrownBy(() -> CollectionValidator.validateDuplicateNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 중복이 있을 수 없다.")
    @Test
    void validateDuplicateNumbers_duplicate_throwsException() {
        // given
        List<Integer> numbers = List.of(1, 1, 1, 1, 1, 2);

        // expect
        Assertions.assertThatThrownBy(() -> CollectionValidator.validateDuplicateNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
