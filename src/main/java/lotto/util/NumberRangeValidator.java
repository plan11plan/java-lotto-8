package lotto.util;

import java.util.List;

public class NumberRangeValidator {
    public NumberRangeValidator() {
    }

    public static void validateInRange(int number, int inclusiveMin, int inclusiveMax) {
        if (number < inclusiveMin) {
            throw new IllegalArgumentException("[ERROR] %d보다 작을 수 없습니다.".formatted(inclusiveMin));
        }
        if (number > inclusiveMax) {
            throw new IllegalArgumentException("[ERROR] %d보다 클 수 없습니다.".formatted(inclusiveMax));
        }
    }

    public static void validateInRange(List<Integer> numbers, int inclusiveMin, int inclusiveMax) {
        for (Integer number : numbers) {
            if (number < inclusiveMin) {
                throw new IllegalArgumentException("[ERROR] %d보다 작을 수 없습니다.".formatted(inclusiveMin));
            }
            if (number > inclusiveMax) {
                throw new IllegalArgumentException("[ERROR] %d보다 클 수 없습니다.".formatted(inclusiveMax));
            }
        }
    }
    
}
