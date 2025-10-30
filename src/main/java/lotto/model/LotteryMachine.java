package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static lotto.Constants.LOTTO_NUMBERS_SIZE;
import static lotto.Constants.LOTTO_UNIT_PRICE;

import java.util.ArrayList;
import java.util.List;

public class LotteryMachine {

    private final LottoNumberGenerator generator;

    public LotteryMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.generator = lottoNumberGenerator;

    }

    public PurchaseResult purchase(PurchaseMoney purchaseMoney) {
        int count = calculateLottoCount(purchaseMoney);
        List<Lotto> lottos = generateLottos(count);
        return new PurchaseResult(purchaseMoney, lottos);
    }

    private int calculateLottoCount(PurchaseMoney purchaseMoney) {
        return purchaseMoney.money() / LOTTO_UNIT_PRICE;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = generator.generateLottoNumbers(
                    LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_NUMBERS_SIZE);
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

}
