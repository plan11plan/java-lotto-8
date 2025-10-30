package lotto.model;

public enum LotteryRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);


    private final int matchedCount;
    private final boolean bonusNumber;
    private final int prizeMoney;

    LotteryRank(int matchedCount, boolean bonusNumber, int prizeMoney) {
        this.matchedCount = matchedCount;
        this.bonusNumber = bonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isBonusNumber() {
        return bonusNumber;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
