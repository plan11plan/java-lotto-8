package lotto.model;

public class LotteryRankingAlgorithm {
    private final RankingDecisionStrategy strategy;

    public LotteryRankingAlgorithm(RankingDecisionStrategy strategy) {
        this.strategy = strategy;
    }

    public LotteryRank determineRanking(Lotto lotto, WinningLotto winningLotto) {
        int matchedCount = lotto.matchCount(winningLotto.winningNumbers().numbers());
        boolean bonusNumber = lotto.contains(winningLotto.bonusNumber().number());
        return strategy.decide(matchedCount, bonusNumber);
    }
}
