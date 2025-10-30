package lotto.model;

public class LotteryRankingAlgorithm {


    public LotteryRank determineRanking(Lotto lotto, WinningLotto winningLotto) {
        int matchedCount = lotto.matchCount(winningLotto.winningNumbers().numbers());
        boolean bonusNumber = lotto.contains(winningLotto.bonusNumber().number());

        if (matchedCount == 6) {
            return LotteryRank.FIRST;
        } else if (matchedCount == 5 && bonusNumber) {
            return LotteryRank.SECOND;
        } else if (matchedCount == 5 && !bonusNumber) {
            return LotteryRank.THIRD;
        } else if (matchedCount == 4) {
            return LotteryRank.FOURTH;
        } else if (matchedCount == 3) {
            return LotteryRank.FIFTH;
        } else {
            return LotteryRank.NONE;
        }

    }
}
