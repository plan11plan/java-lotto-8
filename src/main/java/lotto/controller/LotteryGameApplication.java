package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.LotteryMachine;
import lotto.model.LotteryRank;
import lotto.model.ProfitCalculator;
import lotto.model.PurchaseMoney;
import lotto.model.PurchaseResult;
import lotto.model.WinningLotto;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LotteryGameApplication {
    private final InputView inputView;
    private final OutputView outputView;
    private final LotteryMachine lotteryMachine;
    private final ProfitCalculator calculator;

    public LotteryGameApplication(InputView inputView, OutputView outputView, LotteryMachine lotteryMachine,
                                  ProfitCalculator profitCalculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lotteryMachine = lotteryMachine;
        this.calculator = profitCalculator;
    }

    public void run() {
        try {
            PurchaseMoney purchaseMoney = inputView.readPurchaseAmount();
            PurchaseResult purchaseResult = lotteryMachine.purchase(purchaseMoney);
            outputView.printLottoPurchaseResults(purchaseResult.getLottoCount(), purchaseResult.lottos());

            WinningNumbers winningNumbers = inputView.readWinningNumbers();
            BonusNumber bonusNumber = inputView.readBonusNumber(winningNumbers);
            WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
            Map<LotteryRank, Integer> rankCounts = calculator.calculateRankCounts(purchaseResult.lottos(),
                    winningLotto);
            int totalPrize = calculator.calculateTotalPrize(rankCounts);
            double profitRate = calculator.calculateProfitRate(purchaseMoney.money(), totalPrize);
            profitRate = calculator.roundToFirstDecimal(profitRate);
            outputView.printWinningStatistics(rankCounts, profitRate);
        } finally {
            Console.close();
        }
    }
}
