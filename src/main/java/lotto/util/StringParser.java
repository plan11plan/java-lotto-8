package lotto.util;

import java.util.Arrays;
import java.util.List;

public class StringParser {
    private StringParser() {
    }

    public static int parseInt(final String string) {
        try {
            return Integer.parseInt(string.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 아닙니다.");
        }
    }

    public static List<Integer> parseToIntegerList(final String string, final String delimiter) {
        return Arrays.stream(string.split(delimiter))
                .map(String::trim)
                .map(StringParser::parseInt)
                .toList();
    }

}
