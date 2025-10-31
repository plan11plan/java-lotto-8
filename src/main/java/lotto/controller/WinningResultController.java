package lotto.controller;

import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.LotteryRank;
import lotto.model.ProfitCalculator;
import lotto.model.PurchaseResult;
import lotto.model.WinningLotto;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class WinningResultController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ProfitCalculator calculator;

    public WinningResultController(InputView inputView, OutputView outputView, ProfitCalculator calculator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculator = calculator;
    }

    public void showtWinningResult(PurchaseResult purchaseResult) {
        WinningNumbers winningNumbers = inputView.readWinningNumbers();
        BonusNumber bonusNumber = inputView.readBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LotteryRank, Integer> rankCounts = calculator.calculateRankCounts(purchaseResult.lottos(), winningLotto);
        int totalPrize = calculator.calculateTotalPrize(rankCounts);
        double profitRate = calculator.calculateProfitRate(
                purchaseResult.purchaseMoney().money(),
                totalPrize
        );
        profitRate = calculator.roundToFirstDecimal(profitRate);

        outputView.printWinningStatistics(rankCounts, profitRate);
    }
}
