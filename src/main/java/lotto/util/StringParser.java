package lotto.util;

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

}
