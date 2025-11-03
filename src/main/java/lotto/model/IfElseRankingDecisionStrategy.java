package lotto.model;

public class IfElseRankingDecisionStrategy implements RankingDecisionStrategy {
    @Override
    public LotteryRank decide(int matchedCount, boolean bonusMatched) {
        if (matchedCount == 6) {
            return LotteryRank.FIRST;
        }
        if (matchedCount == 5 && bonusMatched) {
            return LotteryRank.SECOND;
        }
        if (matchedCount == 5 && !bonusMatched) {
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
