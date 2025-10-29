package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.model.PurchaseAmount;
import lotto.model.WinningNumbers;
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
            } finally {
                System.out.print(System.lineSeparator());
            }
        }
    }

    public WinningNumbers readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 당첨 번호가 비어 있습니다.");
                }
                List<Integer> winningNumbers = StringParser.parseToIntegerList(input, ",");
                return new WinningNumbers(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.print(System.lineSeparator());
            }
        }

    }
}
