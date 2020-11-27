package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoIssuerTest {

    @DisplayName("발급된 로또의 갯수를 테스트한다.")
    @Test
    void issueLottoTest() {
        int money = 9999;
        assertThat(LottoIssuer.issueLottoAsMuchPaid(money).size()).isEqualTo(money / LottoIssuer.LOTTO_PRICE);
    }

}