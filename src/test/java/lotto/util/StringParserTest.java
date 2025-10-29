package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringParserTest {

    @DisplayName("문자열을 정수로 변환")
    @Nested
    class 문자열을_정수로_변환 {
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

    @DisplayName("문자열을 정수 리스트로 변환하는 기능")
    @Nested
    class 문자열을_정수리스트로_변환 {
        @DisplayName("구분자로 구분된 문자열을 정수 리스트로 변환한다")
        @Test
        void parseToIntegerList() {
            // given
            String string = "1,2,3,4,5,6";
            String delimiter = ",";

            // when
            List<Integer> result = StringParser.parseToIntegerList(string, delimiter);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @DisplayName("공백이 포함된 문자열을 정수 리스트로 변환한다")
        @Test
        void parseToIntegerList_withSpaces() {
            // given
            String string = "1, 2, 3, 4, 5, 6";
            String delimiter = ",";

            // when
            List<Integer> result = StringParser.parseToIntegerList(string, delimiter);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @DisplayName("단일 숫자를 정수 리스트로 변환한다")
        @Test
        void parseToIntegerList_single() {
            // given
            String string = "42";
            String delimiter = ",";

            // when
            List<Integer> result = StringParser.parseToIntegerList(string, delimiter);

            // then
            assertThat(result).containsExactly(42);
        }

        @DisplayName("음수를 포함한 문자열을 정수 리스트로 변환한다")
        @Test
        void parseToIntegerList_withNegative() {
            // given
            String string = "-1,2,-3,4";
            String delimiter = ",";

            // when
            List<Integer> result = StringParser.parseToIntegerList(string, delimiter);

            // then
            assertThat(result).containsExactly(-1, 2, -3, 4);
        }

        @DisplayName("예외: 숫자가 아닌 값이 포함되면 변환할 수 없다")
        @Test
        void parseToIntegerList_notNumber_throwsException() {
            // given
            String string = "1,2,abc,4";
            String delimiter = ",";

            // expect
            assertThatThrownBy(() -> StringParser.parseToIntegerList(string, delimiter))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @DisplayName("예외: 빈 문자열이 포함되면 변환할 수 없다")
        @Test
        void parseToIntegerList_empty_throwsException() {
            // given
            String string = "1,2,,4";
            String delimiter = ",";

            // expect
            assertThatThrownBy(() -> StringParser.parseToIntegerList(string, delimiter))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

    }

}
