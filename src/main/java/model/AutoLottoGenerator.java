package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static model.Lotto.LOTTO_SIZE;

public class AutoLottoGenerator implements LottoGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int LOTTO_NUMBER_BOUND = 46;

    @Override
    public Lotto generate() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_SIZE) {
            int number = ThreadLocalRandom.current().nextInt(MIN_LOTTO_NUMBER, LOTTO_NUMBER_BOUND);
            LottoNumber lottoNumber = LottoNumber.from(number);
            lottoNumbers.add(lottoNumber);
        }
        return new Lotto(new ArrayList<>(lottoNumbers));
    }

}
