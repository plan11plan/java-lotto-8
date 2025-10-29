package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringParserTest {
    @DisplayName("문자열을 정수로 변환한다")
    @ParameterizedTest
    @CsvSource({
            "123, 123",
            "' 123 ', 123",
            "'+7', 7",
            "'0', 0",
            "'-42', -42"
    })
    void parseInt(String given, int expected) {
        // when
        int result = StringParser.parseInt(given);

        // then
        assertThat(result).isEqualTo(expected);
    }


    @DisplayName("예외: 숫자 형식이 아닌 문자열은 변환할 수 없다")
    @Test
    void parseInt_notNumber_throwsException() {
        // given
        String given = "abc";

        // expect
        assertThatThrownBy(() -> StringParser.parseInt(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 빈 문자열은 변환할 수 없다")
    @Test
    void parseInt_empty_throwsException() {
        // given
        String given = "";

        // expect
        assertThatThrownBy(() -> StringParser.parseInt(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 공백만 있는 문자열은 변환할 수 없다")
    @Test
    void parseInt_blank_throwsException() {
        // given
        String given = "   ";

        // expect
        assertThatThrownBy(() -> StringParser.parseInt(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
