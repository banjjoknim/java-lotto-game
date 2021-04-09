package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LottoIssuerTest {
    private static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);

    @DisplayName("Lottos 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 0, 5000, 11111, 5000000})
    void issueLottos(int amount) {
        // given
        Money money = new Money(new BigDecimal(amount));
        int issueCount = money.getAmount().divide(LOTTO_PRICE).intValue();

        // when
        Lottos lottos = LottoIssuer.issueLottos(money);

        // then
        assertThat(lottos.getLottos()).hasSize(issueCount);
    }
}