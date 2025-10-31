package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.model.PurchaseResult;

public class LotteryGameApplication {
    private final LottoPurchaseController purchaseController;
    private final WinningResultController winningController;

    public LotteryGameApplication(
            LottoPurchaseController purchaseController,
            WinningResultController winningController
    ) {
        this.purchaseController = purchaseController;
        this.winningController = winningController;
    }

    public void run() {
        try {
            PurchaseResult purchaseResult = purchaseController.requestPurchaseLottos();
            winningController.showtWinningResult(purchaseResult);
        } finally {
            Console.close();
        }
    }
}
