package lotto.model;

import static lotto.Constants.LOTTO_MAX_NUMBER;
import static lotto.Constants.LOTTO_MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @DisplayName("유효한 보너스 번호로 객체를 생성한다")
    @Test
    void createBonusNumber() {
        // given
        int given = 7;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatCode(() -> BonusNumber.from(given, winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("경계값(1, 45)으로 보너스 번호 객체를 생성한다")
    @Test
    void createBonusNumber_boundary() {
        // given
        int minNumber = LOTTO_MIN_NUMBER;
        int maxNumber = LOTTO_MAX_NUMBER;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(2, 3, 4, 5, 6, 7));

        // when & then
        assertThatCode(() -> BonusNumber.from(minNumber, winningNumbers))
                .doesNotThrowAnyException();
        assertThatCode(() -> BonusNumber.from(maxNumber, winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("예외: 보너스 번호가 1 미만일 수 없다")
    @Test
    void createBonusNumber_lessThanMin_throwsException() {
        // given
        int number = 0;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // expect
        assertThatThrownBy(() -> BonusNumber.from(number, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("예외: 보너스 번호가 45 초과일 수 없다")
    @Test
    void createBonusNumber_moreThanMax_throwsException() {
        // given
        int given = 46;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // expect
        assertThatThrownBy(() -> BonusNumber.from(given, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호에 포함되지 않으면 정상 처리")
    @Test
    void createBonusNumber_notDuplicate() {
        // given
        int given = 7;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        BonusNumber bonusNumber = BonusNumber.from(given, winningNumbers);

        // then
        assertThat(bonusNumber.number()).isEqualTo(7);
    }

    @DisplayName("예외: 당첨 번호와 중복될 수 없다")
    @Test
    void createBonusNumber_duplicate_throwsException() {
        // given
        int given = 1;
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // expect
        assertThatThrownBy(() -> BonusNumber.from(given, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }


}
