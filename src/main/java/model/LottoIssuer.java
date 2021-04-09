package model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoIssuer {
    private static final BigDecimal LOTTO_PRICE = new BigDecimal(1000);
    private static final String SEPARATOR = ",";

    public static Lottos issueLottos(Money money) {
        int issueCount = calculateIssueCount(money);
        List<Lotto> lottos = generateLottos(issueCount);
        return new Lottos(lottos);
    }

    private static List<Lotto> generateLottos(int issueCount) {
        return Stream.generate(LottoIssuer::generateLotto)
                .limit(issueCount)
                .collect(toList());
    }

    private static Lotto generateLotto() {
        try {
            return new Lotto(Lotto.generateLottoNumbers());
        } catch (IllegalArgumentException e) {
            return generateLotto();
        }
    }

    private static int calculateIssueCount(Money money) {
        return money.getAmount()
                .divide(LOTTO_PRICE)
                .intValue();
    }

    public static WinningLotto issueWinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public static Lotto generateLottoByInput(String inputWinningLottoNumbers) {
        return new Lotto(generateWinningLottoNumbers(inputWinningLottoNumbers));
    }

    private static List<LottoNumber> generateWinningLottoNumbers(String inputWinningLottoNumbers) {
        return Arrays.stream(inputWinningLottoNumbers.split(SEPARATOR))
                .map(Integer::valueOf)
                .map(LottoNumber::new)
                .collect(toList());
    }

}
