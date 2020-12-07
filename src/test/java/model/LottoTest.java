package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {
    private static final String LOTTO_NUMBERS_SIZE_MUST_BE_SIX = "로또 번호는 6개의 숫자여야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_BETWEEN_ONE_AND_FOURTYFIVE = "로또 번호는 1부터 45 사이의 숫자로 구성되어야 합니다.";
    private static final String LOTTO_NUMBERS_MUST_NOT_OVERLAP = "로또 번호는 중복된 번호가 존재하면 안됩니다.";

    @DisplayName("적합한 로또 번호로 로또 객체 생성 테스트")
    @Test
    void createLottoTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(numbers).isEqualTo(new Lotto(numbers).getNumbers());
    }

    @DisplayName("6개가 아닌 번호로 로또 객체 생성 테스트")
    @Test
    void NumbersSizeTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(LOTTO_NUMBERS_SIZE_MUST_BE_SIX);
    }

    @DisplayName("중복된 번호로 로또 객체 생성 테스트")
    @Test
    void overlapNumbersTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessage(LOTTO_NUMBERS_MUST_NOT_OVERLAP);
    }

    @DisplayName("1부터 45 사이의 숫자가 아닌 번호가 포함된 로또 객체 생성 테스트")
    @Test
    void isNotBetweenOneAndFourtyFiveNumber() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 46);
        List<Integer> numbers2 = Arrays.asList(-1, 2, 3, 4, 5, 6);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers1))
                .withMessage(LOTTO_NUMBERS_MUST_BETWEEN_ONE_AND_FOURTYFIVE);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers2))
                .withMessage(LOTTO_NUMBERS_MUST_BETWEEN_ONE_AND_FOURTYFIVE);
    }

    @DisplayName("로또가 번호를 포함하고 있는지 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void hasNumberIsTrueTest(int testNumber) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.hasNumber(testNumber)).isTrue();
    }

    @DisplayName("로또가 번호를 포함하지 않는지 테스트한다.")
    @ParameterizedTest
    @ValueSource(ints = {7, 10, 18, 35, 45, 42})
    void hasNumberIsFalseTest(int testNumber) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.hasNumber(testNumber)).isFalse();
    }
}