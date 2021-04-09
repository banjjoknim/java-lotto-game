package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoIssuer {
    private static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);

    public static Lottos issueLottos(Money money) {
        int issueCount = calculateIssueCount(money);
        List<Lotto> lottos = generateLottos(issueCount);
        return new Lottos(lottos);
    }

    private static List<Lotto> generateLottos(int issueCount) {
        return Stream.generate(() -> new Lotto(Lotto.generateLottoNumbers()))
                .limit(issueCount)
                .collect(toList());
    }

    private static int calculateIssueCount(Money money) {
        return money.getAmount()
                .divide(LOTTO_PRICE)
                .intValue();
    }
}
