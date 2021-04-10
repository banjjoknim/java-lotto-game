package model;

import java.util.ArrayList;
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
        ArrayList<Rank> ranks = new ArrayList<>(Arrays.asList(Rank.values()));
        if (!hasBonusNo) {
            ranks.remove(Rank.SECOND);
        }
        return ranks.stream()
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElseGet(() -> Rank.NONE);
    }

}
