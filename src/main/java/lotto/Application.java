package lotto;

import java.util.Map;
import lotto.model.BonusNumber;
import lotto.model.LotteryMachine;
import lotto.model.LotteryRank;
import lotto.model.LotteryRankingAlgorithm;
import lotto.model.Lotto;
import lotto.model.LottoNumberGenerator;
import lotto.model.ProfitCalculator;
import lotto.model.PurchaseMoney;
import lotto.model.PurchaseResult;
import lotto.model.WinningLotto;
import lotto.model.WinningNumbers;
import lotto.model.WoowaLottoNumberGenerator;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        PurchaseMoney purchaseMoney = inputView.readPurchaseAmount();

        LottoNumberGenerator lottoNumberGenerator = new WoowaLottoNumberGenerator();
        LotteryMachine lotteryMachine = new LotteryMachine(lottoNumberGenerator);
        PurchaseResult purchaseResult = lotteryMachine.purchase(purchaseMoney);

        System.out.println(purchaseResult.getLottoCount() + "개를 구매했습니다.");
        for (Lotto lotto : purchaseResult.getLottos()) {
            System.out.println(lotto.numbers());
        }
        WinningNumbers winningNumbers = inputView.readWinningNumbers();

        BonusNumber bonusNumber = inputView.readBonusNumber(winningNumbers);

        ProfitCalculator profitCalculator = new ProfitCalculator(new LotteryRankingAlgorithm());
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LotteryRank, Integer> rankCounts = profitCalculator.calculateRankCounts(purchaseResult.lottos(),
                winningLotto);

        System.out.println("당첨 통계");
        System.out.println("---");

        System.out.println("3개 일치 (" + String.format("%,d", LotteryRank.FIFTH.getPrizeMoney()) + "원) - "
                + rankCounts.get(LotteryRank.FIFTH) + "개");
        System.out.println("4개 일치 (" + String.format("%,d", LotteryRank.FOURTH.getPrizeMoney()) + "원) - "
                + rankCounts.get(LotteryRank.FOURTH) + "개");
        System.out.println("5개 일치 (" + String.format("%,d", LotteryRank.THIRD.getPrizeMoney()) + "원) - "
                + rankCounts.get(LotteryRank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (" + String.format("%,d", LotteryRank.SECOND.getPrizeMoney()) + "원) - "
                + rankCounts.get(LotteryRank.SECOND) + "개");
        System.out.println("6개 일치 (" + String.format("%,d", LotteryRank.FIRST.getPrizeMoney()) + "원) - "
                + rankCounts.get(LotteryRank.FIRST) + "개");

        int totalPrize = profitCalculator.calculateTotalPrize(rankCounts);
        double profitRate = profitCalculator.calculateProfitRate(purchaseMoney.money(), totalPrize);
        profitRate = profitCalculator.roundToFirstDecimal(profitRate);
        System.out.println("총 수익률은 " + profitRate + "%입니다.");

    }
}
