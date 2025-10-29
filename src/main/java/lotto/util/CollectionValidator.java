package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionValidator {
    private CollectionValidator() {
    }

    public static void validateDuplicateNumbers(final List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (numbers.size() != distinctNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 숫자가 존재합니다.");
        }
    }
    
}
