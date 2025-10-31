package lotto.model;

public class LotteryRankingAlgorithm {


    public LotteryRank determineRanking(Lotto lotto, WinningLotto winningLotto) {
        int matchedCount = lotto.matchCount(winningLotto.winningNumbers().numbers());
        boolean bonusNumber = lotto.contains(winningLotto.bonusNumber().number());

        if (matchedCount == 6) {
            return LotteryRank.FIRST;
        }
        if (matchedCount == 5 && bonusNumber) {
            return LotteryRank.SECOND;
        }
        if (matchedCount == 5 && !bonusNumber) {
            return LotteryRank.THIRD;
        }
        if (matchedCount == 4) {
            return LotteryRank.FOURTH;
        }
        if (matchedCount == 3) {
            return LotteryRank.FIFTH;
        }
        return LotteryRank.NONE;
    }
}
