package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("WinningLotto 객체의 초기화된 상태값을 테스트한다.")
    @Test
    void createWinningLottoTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        int bonusNo = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);
        assertThat(winningLotto.getLotto().getNumbers()).isEqualTo(numbers);
        assertThat(winningLotto.getBonusNo()).isEqualTo(bonusNo);
    }

    @DisplayName("WinningLotto와 userLotto의 번호 일치여부 검증메서드를 테스트한다.")
    @ParameterizedTest
    @CsvFileSource(resources = "../../resources/matchTest.csv", delimiter = ':')
    void matchTest(String testCase, String rankName) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> userNumbers = Arrays.stream(testCase.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());

        Lotto lotto = new Lotto(numbers);
        Lotto userLotto = new Lotto(userNumbers);
        int bonusNo = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);

        assertThat(winningLotto.match(userLotto)).isEqualTo(Rank.valueOf(rankName));
    }

    @DisplayName("보너스 번호를 포함할 때 WinningLotto와 userLotto의 번호 일치여부 검증메서드를 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void matchTestWhenHasBonusNo(int testCase) {
        boolean hasBonusNo = true;
        int matchCount = testCase;
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(reverseOrder())
                .collect(toList());
        if (testCase < 3) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(ranks.get(0));
        }
        if (testCase == 3) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FIFTH);
        }
        if (testCase == 4) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FOUTH);
        }
        if (testCase == 5) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.SECOND);
        }
        if (testCase == 6) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FIRST);
        }
    }

    @DisplayName("보너스 번호를 포함하지 않을 때 일치하는 번호의 갯수로 당첨 등수를 판별하는 기능을 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void getRankByMatchCountAndNotHasBonusNoTest(int testCase) {
        boolean hasBonusNo = false;
        int matchCount = testCase;
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(reverseOrder())
                .collect(toList());
        if (testCase < 3) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(ranks.get(0));
        }
        if (testCase == 3) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FIFTH);
        }
        if (testCase == 4) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FOUTH);
        }
        if (testCase == 5) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.THIRD);
        }
        if (testCase == 6) {
            assertThat(Rank.getRankByMatchCount(matchCount, hasBonusNo)).isEqualTo(Rank.FIRST);
        }
    }

    @DisplayName("WinningLotto의 보너스 볼 검증 메서드를 테스트한다.")
    @Test
    void validateBonusNoTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNo = 6;
        assertThatThrownBy(() -> new WinningLotto(new Lotto(numbers), bonusNo))
                .isInstanceOf(IllegalArgumentException.class);
    }
}