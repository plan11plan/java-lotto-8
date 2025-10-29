package lotto.util;

import java.util.List;

public class NumberValidator {
    public NumberValidator() {
    }

    public static void validateInRange(final int number, final int inclusiveMin, final int inclusiveMax) {
        if (number < inclusiveMin) {
            throw new IllegalArgumentException("[ERROR] %d보다 작을 수 없습니다.".formatted(inclusiveMin));
        }
        if (number > inclusiveMax) {
            throw new IllegalArgumentException("[ERROR] %d보다 클 수 없습니다.".formatted(inclusiveMax));
        }
    }

    public static void validateInRange(final List<Integer> numbers, final int inclusiveMin, final int inclusiveMax) {
        for (Integer number : numbers) {
            if (number < inclusiveMin) {
                throw new IllegalArgumentException("[ERROR] %d보다 작을 수 없습니다.".formatted(inclusiveMin));
            }
            if (number > inclusiveMax) {
                throw new IllegalArgumentException("[ERROR] %d보다 클 수 없습니다.".formatted(inclusiveMax));
            }
        }
    }

    public static void validateDivisible(final int dividend, final int divisor) {
        if (dividend % divisor != 0) {
            throw new IllegalArgumentException("[ERROR] %d는 %d로 나누어 떨어지지 않는 수 입니다.".formatted(dividend, divisor));
        }
    }

}
