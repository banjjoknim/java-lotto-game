package model;

import java.util.ArrayList;
import java.util.List;

public class LottoIssuer {
    public static final int LOTTO_PRICE = 1000;

    public static List<Lotto> issueLottoAsMuchPaid(int money) {
        List<Lotto> lottos = new ArrayList<>();
        while (lottos.size() < money / LOTTO_PRICE) {
            issueLottoThenAddToLottos(lottos);
        }
        return lottos;
    }

    private static void issueLottoThenAddToLottos(List<Lotto> lottos) {
        try {
            lottos.add(issueLotto());
        } catch (IllegalArgumentException e) {
            issueLottoThenAddToLottos(lottos);
        }
    }

    private static Lotto issueLotto() {
        List<Integer> lottoNumbers = LottoNumbers.createNumbers();
        return new Lotto(lottoNumbers);
    }
}
