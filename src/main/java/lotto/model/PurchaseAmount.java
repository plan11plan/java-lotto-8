package lotto.model;

import static lotto.Constants.LOTTO_UNIT_PRICE;
import static lotto.Constants.PURCHASE_MAX_MONEY;
import static lotto.Constants.PURCHASE_MIN_MONEY;

import lotto.util.NumberValidator;

public record PurchaseAmount(int money) {
    public PurchaseAmount {
        NumberValidator.validateDivisible(money, LOTTO_UNIT_PRICE);
        NumberValidator.validateInRange(money, PURCHASE_MIN_MONEY, PURCHASE_MAX_MONEY);
    }
}
