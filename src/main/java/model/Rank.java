package model;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Rank {
    FIRST(6, new Money(new BigDecimal(2_000_000_000))),
    SECOND(5, new Money(new BigDecimal(30_000_000))),
    THIRD(5, new Money(new BigDecimal(1_500_000))),
    FOURTH(4, new Money(new BigDecimal(50_000))),
    FIFTH(3, new Money(new BigDecimal(5_000))),
    NONE(0, new Money(BigDecimal.ZERO));

    private final int matchCount;
    private final Money winningMoney;

    Rank(int matchCount, Money winningMoney) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public static Rank findMatchRank(int matchCount, boolean hasBonusNo) {
        if (matchCount == SECOND.matchCount) {
            return determineSecondOrThird(hasBonusNo);
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(Rank.NONE);
    }

    private static Rank determineSecondOrThird(boolean hasBonusNo) {
        if (hasBonusNo) {
            return SECOND;
        }
        return THIRD;
    }

    public Money calculateBenefit(int winningCount) {
        return winningMoney.multiplyCount(winningCount);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }
}
