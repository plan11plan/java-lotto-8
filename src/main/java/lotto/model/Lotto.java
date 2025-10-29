package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static lotto.Constants.LOTTO_NUMBERS_SIZE;

import java.util.List;
import java.util.Objects;
import lotto.util.CollectionValidator;
import lotto.util.NumberValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validateNotNull(numbers);
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private void validateNotNull(List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 비어있습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (CollectionValidator.hasSize(numbers, LOTTO_NUMBERS_SIZE)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 %d개여야 합니다.".formatted(LOTTO_NUMBERS_SIZE));
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (CollectionValidator.isDuplicateNumbers(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 중복 될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (!NumberValidator.areInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또번호는 %d ~ %d값까지 유효합니다.".formatted(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER));
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public List<Integer> numbers() {
        return numbers;
    }
}
