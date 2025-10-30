package lotto.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("WinningNumbers와 BonusNumber를 받아 당첨 정보를 생성한다.")
    @Test
    void createWinningLotto() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 43, 44, 45));
        BonusNumber bonusNumber = BonusNumber.from(10, winningNumbers);

        // expect
        Assertions.assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
        ;
    }

}
