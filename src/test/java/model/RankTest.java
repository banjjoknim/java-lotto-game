package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class RankTest {

    @DisplayName("보너스볼을 포함할 때 matchCount 에 따른 랭크 결정을 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"THIRD"}, mode = EXCLUDE)
    void getRankHasBonusNoTest(Rank rank) {
        // given
        boolean hasBonusNo = true;

        // when
        Rank getRank = Rank.findMatchRank(rank.getMatchCount(), hasBonusNo);

        // then
        assertThat(getRank).isEqualTo(rank);
    }

    @DisplayName("보너스볼을 포함하지 않을 때 matchCount 에 따른 랭크 결정을 테스트 한다.")
    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"SECOND"}, mode = EXCLUDE)
    void getRankHasNotBonusNoTest(Rank rank) {
        // given
        boolean hasBonusNo = false;

        // when
        Rank getRank = Rank.findMatchRank(rank.getMatchCount(), hasBonusNo);

        // then
        assertThat(getRank).isEqualTo(rank);
    }

}