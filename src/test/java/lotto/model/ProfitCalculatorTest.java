package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
    private final ProfitCalculator calculator = new ProfitCalculator();

    @DisplayName("등수별 당첨 개수를 집계한다")
    @Test
    void calculateRankCounts() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),// 5등
                new Lotto(List.of(1, 2, 4, 8, 9, 10)) // 5등
        );

        // when
        Map<LotteryRank, Integer> rankCounts = calculator.calculateRankCounts(lottos, winningLotto);

        // then
        assertThat(rankCounts.get(LotteryRank.FIRST)).isEqualTo(1);
        assertThat(rankCounts.get(LotteryRank.SECOND)).isEqualTo(1);
        assertThat(rankCounts.get(LotteryRank.THIRD)).isEqualTo(0);
        assertThat(rankCounts.get(LotteryRank.FOURTH)).isEqualTo(0);
        assertThat(rankCounts.get(LotteryRank.FIFTH)).isEqualTo(2);
    }

}
