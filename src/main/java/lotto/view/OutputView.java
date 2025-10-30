package lotto.view;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import lotto.model.LotteryRank;
import lotto.model.Lotto;


public class OutputView {
    private final GameResultFormatter formatter;
    private final PrintStream printStream;

    public OutputView() {
        this(new GameResultFormatter(), System.out);
    }

    public OutputView(OutputStream outputStream) {
        this(new GameResultFormatter(), outputStream);
    }

    public OutputView(GameResultFormatter formatter, OutputStream outputStream) {
        this.formatter = formatter;
        this.printStream = new PrintStream(outputStream, true);
    }


    public void printLottoPurchaseResults(int lottoCount, List<Lotto> lottos) {
        printStream.println(formatter.formatLottoPurchaseResults(lottoCount, lottos));
    }

    public void printWinningStatistics(Map<LotteryRank, Integer> rankCounts, double profitRate) {
        printStream.print(formatter.formatWinningStatistics(rankCounts, profitRate));
    }

}
