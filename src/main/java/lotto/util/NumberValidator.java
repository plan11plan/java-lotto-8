package lotto.util;

import java.util.List;

public class NumberValidator {
    private static final String ERROR_LESS_THAN_MIN = "[ERROR] %d보다 작을 수 없습니다.";
    private static final String ERROR_BIGGER_THAN_MAX = "[ERROR] %d보다 클 수 없습니다.";
    private static final String ERROR_NOT_DIVISIBLE = "[ERROR] %d는 %d로 나누어 떨어지지 않는 수 입니다.";

    private NumberValidator() {
    }

    public static void validateInRange(final int number, final int inclusiveMin, final int inclusiveMax) {
        if (number < inclusiveMin) {
            throw new IllegalArgumentException(ERROR_LESS_THAN_MIN.formatted(inclusiveMin));
        }
        if (number > inclusiveMax) {
            throw new IllegalArgumentException(ERROR_BIGGER_THAN_MAX.formatted(inclusiveMax));
        }
    }

    public static void validateInRange(final List<Integer> numbers, final int inclusiveMin, final int inclusiveMax) {
        for (Integer number : numbers) {
            if (number < inclusiveMin) {
                throw new IllegalArgumentException(ERROR_LESS_THAN_MIN.formatted(inclusiveMin));
            }
            if (number > inclusiveMax) {
                throw new IllegalArgumentException(ERROR_BIGGER_THAN_MAX.formatted(inclusiveMax));
            }
        }
    }

    public static boolean areInRange(final List<Integer> numbers, final int inclusiveMin, final int inclusiveMax) {
        for (Integer number : numbers) {
            if (number < inclusiveMin || number > inclusiveMax) {
                return false;
            }
        }
        return true;
    }


    public static void validateDivisible(final int dividend, final int divisor) {
        if (dividend % divisor != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVISIBLE.formatted(dividend, divisor));
        }
    }

}
