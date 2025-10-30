package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.PurchaseMoney;
import lotto.model.WinningNumbers;
import lotto.util.StringParser;

public class InputView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구매금액을 입력해 주세요.";
    private static final String PURCHASE_AMOUNT_RETRY = "구매금액을 다시 입력해 주세요.";

    private static final String WINNING_NUMBERS_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String WINNING_NUMBERS_RETRY = "당첨 번호를 다시 입력해 주세요.";

    private static final String BONUS_NUMBER_PROMPT = "보너스 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_RETRY = "보너스 번호를 다시 입력해 주세요.";

    private static final String ERR_PURCHASE_AMOUNT_EMPTY = "[ERROR] 구매금액이 비어 있습니다.";
    private static final String ERR_WINNING_NUMBERS_EMPTY = "[ERROR] 당첨 번호가 비어 있습니다.";
    private static final String ERR_BONUS_NUMBER_EMPTY = "[ERROR] 보너스 번호가 비어 있습니다.";

    private static final String DELIMITER_COMMA = ",";


    public PurchaseMoney readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        while (true) {
            try {
                String input = Console.readLine();
                System.out.println();
                return createPurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(PURCHASE_AMOUNT_RETRY);
            }
        }
    }

    private PurchaseMoney createPurchaseAmount(String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERR_PURCHASE_AMOUNT_EMPTY);
        }
        int purchaseAmount = StringParser.parseInt(input);
        return new PurchaseMoney(purchaseAmount);
    }

    public WinningNumbers readWinningNumbers() {
        System.out.println(WINNING_NUMBERS_PROMPT);
        while (true) {
            try {
                String input = Console.readLine();
                System.out.println();
                return createWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(WINNING_NUMBERS_RETRY);
            }
        }
    }

    private WinningNumbers createWinningNumbers(String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERR_WINNING_NUMBERS_EMPTY);
        }
        List<Integer> winningNumbers = StringParser.parseToIntegerList(input, DELIMITER_COMMA);
        return new WinningNumbers(winningNumbers);
    }

    public BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        System.out.println(BONUS_NUMBER_PROMPT);
        while (true) {
            try {
                String input = Console.readLine();
                System.out.println();
                return createBonusNumber(winningNumbers, input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(BONUS_NUMBER_RETRY);
            }
        }
    }

    private BonusNumber createBonusNumber(WinningNumbers winningNumbers, String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERR_BONUS_NUMBER_EMPTY);
        }
        int bonusNumber = StringParser.parseInt(input);
        return BonusNumber.from(bonusNumber, winningNumbers);
    }
}
