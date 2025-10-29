package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.model.PurchaseAmount;
import lotto.util.StringParser;

public class InputView {

    public PurchaseAmount readPurchaseAmount() {
        System.out.println("구매금액을 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 구매금액이 비어 있습니다.");
                }
                int purchaseAmount = StringParser.parseInt(input);
                return new PurchaseAmount(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
