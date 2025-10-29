package lotto.util;

public class AmountValidator {
    private AmountValidator() {
    }

    public static void validateDivisible(int dividend, int divisor) {
        if (dividend % divisor != 0) {
            throw new IllegalArgumentException("[ERROR] %d는 %d로 나누어 떨어지지 않는 수 입니다.".formatted(dividend, divisor));
        }
    }

}
