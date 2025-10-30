package lotto.view;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static lotto.model.LotteryRank.FIFTH;
import static lotto.model.LotteryRank.FIRST;
import static lotto.model.LotteryRank.FOURTH;
import static lotto.model.LotteryRank.SECOND;
import static lotto.model.LotteryRank.THIRD;

import java.util.List;
import java.util.Map;
import lotto.model.LotteryRank;
import lotto.model.Lotto;

public class GameResultFormatter {
    private static final String PURCHASE_RESULT = "%d개를 구매했습니다.";
    private static final String WINNING_RESULT_TITLE = "당첨 통계";
    private static final String RESULT_LINE = "---";
    private static final String RANK_RESULT_FORMAT = "%d개 일치 (%,d원) - %d개";
    private static final String RANK_RESULT_WITH_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";


    public String formatLottoPurchaseResults(int lottoCount, List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        sb.append(format(PURCHASE_RESULT, lottoCount)).append(lineSeparator());
        for (Lotto lotto : lottos) {
            sb.append(lotto.numbers()).append(lineSeparator());
        }
        sb.append(lineSeparator());
        return sb.toString();
    }


    public String formatWinningStatistics(Map<LotteryRank, Integer> rankCounts, double profitRate) {
        StringBuilder sb = new StringBuilder();
        sb.append(WINNING_RESULT_TITLE).append(lineSeparator());
        sb.append(RESULT_LINE).append(lineSeparator());

        sb.append(format(RANK_RESULT_FORMAT, 3, FIFTH.getPrizeMoney(), rankCounts.get(FIFTH))).append(lineSeparator());
        sb.append(format(RANK_RESULT_FORMAT, 4, FOURTH.getPrizeMoney(), rankCounts.get(FOURTH)))
                .append(lineSeparator());
        sb.append(format(RANK_RESULT_FORMAT, 5, THIRD.getPrizeMoney(), rankCounts.get(THIRD))).append(lineSeparator());
        sb.append(format(RANK_RESULT_WITH_BONUS_FORMAT, 5, SECOND.getPrizeMoney(), rankCounts.get(SECOND)))
                .append(lineSeparator());
        sb.append(format(RANK_RESULT_FORMAT, 6, FIRST.getPrizeMoney(), rankCounts.get(FIRST))).append(lineSeparator());

        sb.append(String.format(PROFIT_RATE_FORMAT, profitRate));
        return sb.toString();
    }
}
