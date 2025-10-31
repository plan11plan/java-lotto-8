package lotto.controller;

import lotto.model.LotteryMachine;
import lotto.model.PurchaseMoney;
import lotto.model.PurchaseResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoPurchaseController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LotteryMachine lotteryMachine;

    public LottoPurchaseController(InputView inputView, OutputView outputView, LotteryMachine lotteryMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lotteryMachine = lotteryMachine;
    }

    public PurchaseResult requestPurchaseLottos() {
        PurchaseMoney purchaseMoney = inputView.readPurchaseAmount();
        PurchaseResult purchaseResult = lotteryMachine.purchase(purchaseMoney);
        outputView.printLottoPurchaseResults(
                purchaseResult.getLottoCount(),
                purchaseResult.lottos()
        );
        return purchaseResult;
    }

}
