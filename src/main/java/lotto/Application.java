package lotto;

import lotto.model.PurchaseAmount;
import lotto.model.WinningNumbers;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        PurchaseAmount purchaseAmount = inputView.readPurchaseAmount();
        System.out.println(purchaseAmount.money());

        WinningNumbers winningNumbers = inputView.readWinningNumbers();
        System.out.println(winningNumbers.numbers().toString());
    }
}
