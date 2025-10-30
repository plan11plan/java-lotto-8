package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.LotteryRank;
import lotto.model.Lotto;

public class GameResultFormatter {

    public String formatLottoPurchaseResults(int lottoCount, List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        sb.append(lottoCount).append("개를 구매했습니다.").append(System.lineSeparator());
        for (Lotto lotto : lottos) {
            sb.append(lotto.numbers()).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }


    public String formatWinningStatistics(Map<LotteryRank, Integer> rankCounts, double profitRate) {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계").append(System.lineSeparator());
        sb.append("---").append(System.lineSeparator());
        sb.append("3개 일치 (").append(String.format("%,d", LotteryRank.FIFTH.getPrizeMoney())).append("원) - ")
                .append(rankCounts.get(LotteryRank.FIFTH)).append("개").append(System.lineSeparator());
        sb.append("4개 일치 (").append(String.format("%,d", LotteryRank.FOURTH.getPrizeMoney())).append("원) - ")
                .append(rankCounts.get(LotteryRank.FOURTH)).append("개").append(System.lineSeparator());
        sb.append("5개 일치 (").append(String.format("%,d", LotteryRank.THIRD.getPrizeMoney())).append("원) - ")
                .append(rankCounts.get(LotteryRank.THIRD)).append("개").append(System.lineSeparator());
        sb.append("5개 일치, 보너스 볼 일치 (").append(String.format("%,d", LotteryRank.SECOND.getPrizeMoney())).append("원) - ")
                .append(rankCounts.get(LotteryRank.SECOND)).append("개").append(System.lineSeparator());
        sb.append("6개 일치 (").append(String.format("%,d", LotteryRank.FIRST.getPrizeMoney())).append("원) - ")
                .append(rankCounts.get(LotteryRank.FIRST)).append("개").append(System.lineSeparator());
        sb.append("총 수익률은 ").append(profitRate).append("%입니다.");
        return sb.toString();
    }
}
