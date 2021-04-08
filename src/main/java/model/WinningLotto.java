package model;

public class WinningLotto {
    private static final String BONUS_BALL_NUMBER_IS_DUPLICATE = "보너스 볼 번호가 당첨 로또 번호와 중복됩니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNo;

    public WinningLotto(Lotto lotto, LottoNumber bonusNo) {
        validateBonusNo(lotto, bonusNo);
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void validateBonusNo(Lotto lotto, LottoNumber bonusNo) {
        if (lotto.getNumbers().contains(bonusNo)) {
            throw new IllegalArgumentException(BONUS_BALL_NUMBER_IS_DUPLICATE);
        }
    }

    public Rank match(Lotto userLotto) {
        return null;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNo() {
        return bonusNo;
    }

}
