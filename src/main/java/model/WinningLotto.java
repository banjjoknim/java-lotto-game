package model;

public class WinningLotto {
    private static final String BONUS_BALL_NUMBER_IS_DUPLICATE = "보너스 볼 번호가 당첨 로또 번호와 중복됩니다.";

    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        validateBonusNo(lotto, bonusNo);
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void validateBonusNo(Lotto lotto, int bonusNo) {
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNo);
        if (lotto.getNumbers().contains(bonusLottoNumber)) {
            throw new IllegalArgumentException(BONUS_BALL_NUMBER_IS_DUPLICATE);
        }
    }

    public Rank match(Lotto userLotto) {
        return null;
    }

}
