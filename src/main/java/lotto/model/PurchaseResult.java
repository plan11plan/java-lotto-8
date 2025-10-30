package lotto.model;

import java.util.List;

public record PurchaseResult(PurchaseMoney purchaseMoney, List<Lotto> lottos) {

    public int getPurchaseAmount() {
        return purchaseMoney.money();
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
