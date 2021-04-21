package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoGeneratorTest {
    private static final int LOTTO_SIZE = 6;

    @DisplayName("랜덤한 값으로 Lotto 를 생성하는 전략을 테스트 한다.")
    @Test
    void generateAutoLottoTest() {
        // given
        LottoGenerator lottoGenerator = new AutoLottoGenerator();

        // when
        Lotto lotto = lottoGenerator.generate();

        // then
        assertAll(
                () -> assertThat(lotto).isInstanceOf(Lotto.class),
                () -> assertThat(lotto.getNumbers()).hasSize(LOTTO_SIZE)
        );
    }

}