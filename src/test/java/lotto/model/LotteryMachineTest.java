package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static lotto.Constants.LOTTO_NUMBERS_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryMachineTest {
    private final LottoNumberGenerator generator = new WoowaLottoNumberGenerator();

    @DisplayName("구매 금액을 로또 개수로 올바르게 변환한다")
    @Test
    void purchase_convertsMoneyToLottoCount() {
        // given
        LotteryMachine machine = new LotteryMachine(generator);

        // expect
        assertAll(
                () -> assertThat(machine.purchase(new PurchaseMoney(3_000)).getLottoCount()).isEqualTo(3),
                () -> assertThat(machine.purchase(new PurchaseMoney(10_000)).getLottoCount()).isEqualTo(10)
        );
    }

    @DisplayName("로또 개수만큼 로또를 생성한다")
    @Test
    void purchase_generatesExactNumberOfLottos() {
        // given
        LotteryMachine machine = new LotteryMachine(generator);
        PurchaseMoney money = new PurchaseMoney(7_000);

        // when
        PurchaseResult result = machine.purchase(money);

        assertThat(result.getLottos()).hasSize(7);
        assertThat(result.getLottos()).hasSize(7)
                .allMatch(lotto -> lotto.numbers().size() == LOTTO_NUMBERS_SIZE);
    }

    @DisplayName("생성된 각 로또는 유효한 로또다")
    @Test
    void purchase_generatesValidLottos() {
        // given
        LotteryMachine machine = new LotteryMachine(generator);
        PurchaseMoney money = new PurchaseMoney(3_000);

        // when
        PurchaseResult result = machine.purchase(money);

        // then
        assertAll(
                () -> assertEquals(3, result.lottos().size()),
                () -> assertTrue(result.lottos().stream().allMatch(
                        l -> l.numbers().size() == LOTTO_NUMBERS_SIZE)),
                () -> assertTrue(result.lottos().stream().flatMap(l -> l.numbers().stream())
                        .allMatch(n -> n >= LOTTO_MIN_NUMBER && n <= LOTTO_MAX_NUMBER)),
                () -> assertTrue(result.lottos().stream()
                        .allMatch(l -> l.numbers().size() == new HashSet<>(l.numbers()).size()))
        );
    }

}
