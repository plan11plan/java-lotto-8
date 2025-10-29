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
    
}
