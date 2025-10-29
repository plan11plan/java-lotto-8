package lotto.model;

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
            throw new IllegalArgumentException("[ERROR] 당첨번호와 중복된 보너스번호 입니다.");
        }
        return new BonusNumber(number);
    }

    public int number() {
        return this.number;
    }
}
