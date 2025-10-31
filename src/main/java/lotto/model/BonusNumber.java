package lotto.model;

import static lotto.model.LottoErrorType.DUPLICATE_WITH_WINNING_NUMBER;

import lotto.Constants;
import lotto.util.NumberValidator;

public class BonusNumber {

    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber from(int number, WinningNumbers winningNumbers) {
        NumberValidator.validateInRange(number, Constants.LOTTO_MIN_NUMBER, Constants.LOTTO_MAX_NUMBER);

        if (winningNumbers.numbers().contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_WITH_WINNING_NUMBER.getMessage());
        }
        return new BonusNumber(number);
    }

    public int number() {
        return this.number;
    }
}
