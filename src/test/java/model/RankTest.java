package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("보너스볼을 포함할 때 matchCount 에 따른 랭크 결정을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void getRankHasBonusNoTest(int matchCount) {
        // given
        boolean hasBonusNo = true;
        Rank rank = setUpRank(matchCount, hasBonusNo);

        // when
        Rank getRank = Rank.getRank(matchCount, hasBonusNo);

        // then
        assertThat(getRank).isEqualTo(rank);
    }

    @DisplayName("보너스볼을 포함하지 않을 때 matchCount 에 따른 랭크 결정을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void getRankHasNotBonusNoTest(int matchCount) {
        // given
        boolean hasBonusNo = false;
        Rank rank = setUpRank(matchCount, hasBonusNo);

        // when
        Rank getRank = Rank.getRank(matchCount, hasBonusNo);

        // then
        assertThat(getRank).isEqualTo(rank);
    }

    private Rank setUpRank(int matchCount, boolean hasBonusNo) {
        Rank rank = Rank.NONE;
        if (matchCount == Rank.FIFTH.getMatchCount()) {
            rank = Rank.FIFTH;
        }
        if (matchCount == Rank.FOURTH.getMatchCount()) {
            rank = Rank.FOURTH;
        }
        if (matchCount == Rank.THIRD.getMatchCount()) {
            rank = Rank.THIRD;
        }
        if (matchCount == Rank.SECOND.getMatchCount() && hasBonusNo) {
            rank = Rank.SECOND;
        }
        if (matchCount == Rank.FIRST.getMatchCount()) {
            rank = Rank.FIRST;
        }
        return rank;
    }

}