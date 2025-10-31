package lotto;

import lotto.controller.LotteryGameApplication;
import lotto.controller.LottoPurchaseController;
import lotto.controller.WinningResultController;
import lotto.model.LotteryMachine;
import lotto.model.LotteryRankingAlgorithm;
import lotto.model.ProfitCalculator;
import lotto.model.WoowaLottoNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LotteryGameApplication application = new LotteryGameApplication(
                createPurchaseController(inputView, outputView),
                createWinningController(inputView, outputView)
        );
        application.run();
    }

    private static WinningResultController createWinningController(InputView inputView, OutputView outputView) {
        return new WinningResultController(
                inputView,
                outputView,
                new ProfitCalculator(new LotteryRankingAlgorithm())
        );
    }

    private static LottoPurchaseController createPurchaseController(InputView inputView, OutputView outputView) {
        return new LottoPurchaseController(
                inputView,
                outputView,
                new LotteryMachine(new WoowaLottoNumberGenerator())
        );
    }
}
