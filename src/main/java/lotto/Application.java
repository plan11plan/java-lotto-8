package lotto;

import lotto.controller.LotteryGameApplication;
import lotto.model.LotteryMachine;
import lotto.model.LotteryRankingAlgorithm;
import lotto.model.ProfitCalculator;
import lotto.model.WoowaLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LotteryGameApplication application = new LotteryGameApplication(
                new InputView(),
                new OutputView(),
                new LotteryMachine(new WoowaLottoNumberGenerator()),
                new ProfitCalculator(new LotteryRankingAlgorithm())
        );

        application.run();

    }
}
