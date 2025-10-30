package lotto.model;

import lotto.Constants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseMoneyTest {
    private final static int VALID_PURCHASE_AMOUNT = Constants.PURCHASE_MIN_MONEY;
    private final static int UNVALID_PURCHASE_AMOUNT_LESS_THAN_MIN = Constants.PURCHASE_MIN_MONEY - 1;
    private final static int UNVALID_PURCHASE_AMOUNT_BIGGER_THAN_MAX = Constants.PURCHASE_MAX_MONEY + 1;


    @DisplayName("유효한 값으로 나누어 떨어지면 정상 처리")
    @Test
    void purchaseAmount_divisable() {
        // given
        int given = VALID_PURCHASE_AMOUNT;

        // expect
        Assertions.assertThatCode(() -> new PurchaseMoney(given))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 유효한 값으로 나누어 떨어지지 않음")
    @Test
    void purchaseAmount_cant_divisable_throwsException() {
        // given
        int given = UNVALID_PURCHASE_AMOUNT_BIGGER_THAN_MAX;

        // expect
        Assertions.assertThatThrownBy(() -> new PurchaseMoney(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("유효한 범위면 정상 처리")
    @Test
    void purchaseAmount_valid_range() {
        // given
        int given = VALID_PURCHASE_AMOUNT;

        // expect
        Assertions.assertThatCode(() -> new PurchaseMoney(given))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 유효한 범위 미만")
    @Test
    void purchaseAmount__LessThanMin_throwsException() {
        // given
        int given = UNVALID_PURCHASE_AMOUNT_LESS_THAN_MIN;

        // expect
        Assertions.assertThatThrownBy(() -> new PurchaseMoney(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 유효한 범위 초과")
    @Test
    void purchaseAmount_BiggerThanMax_throwsException() {
        // given
        int given = UNVALID_PURCHASE_AMOUNT_BIGGER_THAN_MAX;

        // expect
        Assertions.assertThatThrownBy(() -> new PurchaseMoney(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }


}
