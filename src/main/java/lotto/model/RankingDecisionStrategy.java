package lotto.model;

public interface RankingDecisionStrategy {
    LotteryRank decide(int matchedCount, boolean bonusMatched);

}
