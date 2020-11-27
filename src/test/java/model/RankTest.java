package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    private static final int FIRST_WINNING_MONEY = 2000000000;
    private static final int SECOND_WINNING_MONEY = 30000000;
    private static final int THIRD_WINNING_MONEY = 1500000;
    private static final int FOUTH_WINNING_MONEY = 50000;
    private static final int FIFTH_WINNING_MONEY = 5000;
    private static final int NONE_WINNING_MONEY = 0;
    private static final int FIRST_MATCH_COUNT = 6;
    private static final int SECOND_MATCH_COUNT = 5;
    private static final int THIRD_MATCH_COUNT = 5;
    private static final int FOUTH_MATCH_COUNT = 4;
    private static final int FIFTH_MATCH_COUNT = 3;
    private static final int NONE_MATCH_COUNT = 0;

    @DisplayName("Rank 객체의 상태값 중 WINNING_MONEY를 테스트한다.")
    @Test
    void rankWinningMoneyTest() {
        Rank[] ranks = Rank.values();
        int[] winningMoneys = {FIRST_WINNING_MONEY, SECOND_WINNING_MONEY, THIRD_WINNING_MONEY, FOUTH_WINNING_MONEY, FIFTH_WINNING_MONEY, NONE_WINNING_MONEY};

        for (int i = 0; i < ranks.length; i++) {
            assertThat(ranks[i].getWinningMoney()).isEqualTo(winningMoneys[i]);
        }
    }

    @DisplayName("Rank 객체의 상태값 중 MATCH_COUNT를 테스트한다.")
    @Test
    void rankMatchCountTest() {
        Rank[] ranks = Rank.values();
        int[] matchCounts = {FIRST_MATCH_COUNT, SECOND_MATCH_COUNT, THIRD_MATCH_COUNT, FOUTH_MATCH_COUNT, FIFTH_MATCH_COUNT, NONE_MATCH_COUNT};

        for (int i = 0; i < ranks.length; i++) {
            assertThat(ranks[i].getMatchCount()).isEqualTo(matchCounts[i]);
        }
    }
}