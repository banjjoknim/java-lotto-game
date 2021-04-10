package model;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int matchCount;
    private final int winningMoney;

    Rank(int matchCount, int winningMoney) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningMoney() {
        return winningMoney;
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

    public long calculateBenefit(int winningCount) {
        return winningMoney * winningCount;
    }

}
