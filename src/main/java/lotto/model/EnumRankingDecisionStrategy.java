package lotto.model;

public class EnumRankingDecisionStrategy implements RankingDecisionStrategy {

    @Override
    public LotteryRank decide(int matchedCount, boolean bonusMatched) {
        return LotteryRank.from(matchedCount, bonusMatched);
    }
}
