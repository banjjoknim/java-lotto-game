package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static model.Lotto.LOTTO_SIZE;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generate(NumberGenerator numberGenerator) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_SIZE) {
            LottoNumber lottoNumber = LottoNumber.getLottoNumberFromCache(numberGenerator.generate());
            lottoNumbers.add(lottoNumber);
        }
        return new Lotto(new ArrayList<>(lottoNumbers));
    }

}
