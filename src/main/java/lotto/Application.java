package lotto;

import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.LotteryMachine;
import lotto.model.LotteryRank;
import lotto.model.LotteryRankingAlgorithm;
import lotto.model.LottoNumberGenerator;
import lotto.model.ProfitCalculator;
import lotto.model.PurchaseMoney;
import lotto.model.PurchaseResult;
import lotto.model.WinningLotto;
import lotto.model.WinningNumbers;
import lotto.model.WoowaLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PurchaseMoney purchaseMoney = inputView.readPurchaseAmount();

        LottoNumberGenerator lottoNumberGenerator = new WoowaLottoNumberGenerator();
        LotteryMachine lotteryMachine = new LotteryMachine(lottoNumberGenerator);
        PurchaseResult purchaseResult = lotteryMachine.purchase(purchaseMoney);
        outputView.printLottoPurchaseResults(purchaseResult.getLottoCount(), purchaseResult.lottos());
        WinningNumbers winningNumbers = inputView.readWinningNumbers();

        BonusNumber bonusNumber = inputView.readBonusNumber(winningNumbers);

        ProfitCalculator profitCalculator = new ProfitCalculator(new LotteryRankingAlgorithm());
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LotteryRank, Integer> rankCounts = profitCalculator.calculateRankCounts(purchaseResult.lottos(),
                winningLotto);

        int totalPrize = profitCalculator.calculateTotalPrize(rankCounts);
        double profitRate = profitCalculator.calculateProfitRate(purchaseMoney.money(), totalPrize);
        profitRate = profitCalculator.roundToFirstDecimal(profitRate);
        outputView.printWinningStatistics(rankCounts, profitRate);
    }
}
