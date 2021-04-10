package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoneyTest {

    @DisplayName("음수로 Money 생성시 예외를 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -500, -20000})
    void validateMoneyIsNotPositiveTest(int value) {
        // given
        BigDecimal amount = new BigDecimal(value);

        // when

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(amount));
    }

    @DisplayName("유효한 값으로 Money 생성을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1000, 10000, 50000})
    void createMoneyTest(int value) {
        // given
        BigDecimal amount = new BigDecimal(value);
        
        // when
        Money money = new Money(amount);

        // then
        assertThat(money).isInstanceOf(Money.class);
        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @DisplayName("Money 객체에서 로또를 구매하는데 실제 사용된 금액 계산을 테스트 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 1111, 2222, 5555, 10000, 24500})
    void calculateTotalSpendMoneyTest(int amount) {
        // given
        Money money = new Money(new BigDecimal(amount));

        // when
        BigDecimal totalSpendMoney = money.calculateTotalSpendMoney();

        // then
        assertThat(totalSpendMoney).isEqualTo(new BigDecimal(amount).setScale(-3, RoundingMode.FLOOR));
    }

}