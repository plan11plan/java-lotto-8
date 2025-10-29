package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static lotto.Constants.LOTTO_NUMBERS_SIZE;

import java.util.List;
import lotto.util.CollectionValidator;
import lotto.util.NumberValidator;

public record WinningNumbers(List<Integer> numbers) {
    public WinningNumbers {
        CollectionValidator.validateSize(numbers, LOTTO_NUMBERS_SIZE);
        NumberValidator.validateInRange(numbers, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
        CollectionValidator.validateDuplicateNumbers(numbers);
    }
}
