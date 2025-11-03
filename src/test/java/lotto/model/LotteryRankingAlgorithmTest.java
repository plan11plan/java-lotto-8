package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryRankingAlgorithmTest {
    private final LotteryRankingAlgorithm algorithm = new LotteryRankingAlgorithm(new EnumRankingDecisionStrategy());

    @DisplayName("6개 일치 -> 1등")
    @Test
    void determineRanking_6matches_returnsFirst() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FIRST);
    }

    @DisplayName("5개 일치 + 보너스 일치 -> 2등")
    @Test
    void determineRanking_5matchesWithBonus_returnsSecond() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.SECOND);
    }

    @DisplayName("5개 일치하면 -> 3등")
    @Test
    void determineRanking_5matches_returnsThird() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.THIRD);
    }

    @DisplayName("4개 일치하면 -> 4등")
    @Test
    void determineRanking_4matches_returnsFourth() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FOURTH);
    }

    @DisplayName("3개 일치 -> 5등")
    @Test
    void determineRanking_3matches_returnsFifth() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FIFTH);
    }

    @DisplayName("2개 이하 일치 (2개 일치) -> 낙첨")
    @Test
    void determineRanking_2matches_returnsNone() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.NONE);
    }

    @DisplayName("2개 이하 일치 (1개 일치) -> 낙첨")
    @Test
    void determineRanking_1match_returnsNone() {
        // given
        Lotto lotto = new Lotto(List.of(1, 8, 9, 10, 11, 12));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.NONE);
    }

    @DisplayName("2개 이하 일치 (0개 일치) -> 낙첨")
    @Test
    void determineRanking_0match_returnsNone() {
        // given
        Lotto lotto = new Lotto(List.of(10, 11, 12, 13, 14, 15));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.NONE);
    }

    @DisplayName("6개 일치 + 보너스 일치해도 1등으로 판정")
    @Test
    void determineRanking_6matchesWithBonus_returnsFirst() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FIRST);
    }

    @DisplayName("4개 일치 + 보너스 일치해도 4등으로 판정")
    @Test
    void determineRanking_4matchesWithBonus_returnsFourth() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FOURTH);
    }

    @DisplayName("3개 일치 + 보너스 일치해도 5등으로 판정")
    @Test
    void determineRanking_3matchesWithBonus_returnsFifth() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7, winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LotteryRank rank = algorithm.determineRanking(lotto, winningLotto);

        // then
        assertThat(rank).isEqualTo(LotteryRank.FIFTH);
    }

}
