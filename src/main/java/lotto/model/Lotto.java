package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static lotto.Constants.LOTTO_NUMBERS_SIZE;
import static lotto.model.LottoErrorType.DUPLICATE;
import static lotto.model.LottoErrorType.INVALID_RANGE;
import static lotto.model.LottoErrorType.INVALID_SIZE;
import static lotto.model.LottoErrorType.NULL;

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

    public int matchCount(final List<Integer> numbers) {
        int count = 0;
        for (int number : this.numbers) {
            if (numbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> numbers() {
        return numbers;
    }

    private void validateNotNull(List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IllegalArgumentException(NULL.getMessage());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (!CollectionValidator.hasSize(numbers, LOTTO_NUMBERS_SIZE)) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage(LOTTO_NUMBERS_SIZE));
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (CollectionValidator.isDuplicateNumbers(numbers)) {
            throw new IllegalArgumentException(DUPLICATE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (!NumberValidator.areInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER));
        }
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

}
