package lotto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfitCalculator {
    LotteryRankingAlgorithm rankingAlgorithm = new LotteryRankingAlgorithm();

    public Map<LotteryRank, Integer> calculateRankCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<LotteryRank, Integer> rankCounts = initializeRankCounts();

        for (Lotto lotto : lottos) {
            LotteryRank rank = rankingAlgorithm.determineRanking(lotto, winningLotto);
            if (rank != LotteryRank.NONE) {
                rankCounts.put(rank, rankCounts.get(rank) + 1);
            }
        }

        return rankCounts;
    }

    public int calculateTotalPrize(Map<LotteryRank, Integer> rankCounts) {
        int totalPrize = 0;
        for (Map.Entry<LotteryRank, Integer> entry : rankCounts.entrySet()) {
            LotteryRank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += rank.getPrizeMoney() * count;
        }
        return totalPrize;
    }

    public double calculateProfitRate(int purchaseAmount, int totalPrize) {
        return (double) totalPrize / purchaseAmount * 100;
    }

    private Map<LotteryRank, Integer> initializeRankCounts() {
        Map<LotteryRank, Integer> rankCounts = new HashMap<>();
        for (LotteryRank rank : LotteryRank.values()) {
            if (rank != LotteryRank.NONE) {
                rankCounts.put(rank, 0);
            }
        }
        return rankCounts;
    }
}
