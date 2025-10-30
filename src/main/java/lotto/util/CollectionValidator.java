package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionValidator {
    private static final String ERROR_DUPLICATE_NUMBERS = "[ERROR] 중복된 숫자가 존재합니다.";
    private static final String ERROR_SIZE_MISMATCH = "[ERROR] 크기가 일치하지 않습니다.";

    private CollectionValidator() {
    }

    public static void validateDuplicateNumbers(final List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (numbers.size() != distinctNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }
    }

    public static void validateSize(final List<Integer> numbers, final int size) {
        if (numbers.size() != size) {
            throw new IllegalArgumentException(ERROR_SIZE_MISMATCH);
        }
    }

    public static boolean isDuplicateNumbers(final List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        return numbers.size() != distinctNumbers.size();
    }

    public static boolean hasSize(final List<Integer> numbers, final int size) {
        return numbers.size() == size;
    }

}
