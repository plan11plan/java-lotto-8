package lotto.model;

public class SwitchRankingDecisionStrategy implements RankingDecisionStrategy {

    @Override
    public LotteryRank decide(int matchedCount, boolean bonusMatched) {
        return switch (matchedCount) {
            case 6 -> LotteryRank.FIRST;
            case 5 -> {
                if (bonusMatched) {
                    yield LotteryRank.SECOND;
                }
                yield LotteryRank.THIRD;
            }
            case 4 -> LotteryRank.FOURTH;
            case 3 -> LotteryRank.FIFTH;
            default -> LotteryRank.NONE;
        };
    }
}
