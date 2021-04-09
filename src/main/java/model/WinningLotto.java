package model;

public class WinningLotto {
    private static final String BONUS_BALL_NUMBER_IS_DUPLICATE = "보너스 볼 번호가 당첨 로또 번호와 중복됩니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNo;

    public WinningLotto(Lotto lotto, LottoNumber bonusNo) {
        validateBonusNoIsDuplicate(lotto, bonusNo);
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void validateBonusNoIsDuplicate(Lotto lotto, LottoNumber bonusNo) {
        if (lotto.getNumbers().contains(bonusNo)) {
            throw new IllegalArgumentException(BONUS_BALL_NUMBER_IS_DUPLICATE);
        }
    }

    public Rank match(Lotto userLotto) {
        int matchCount = getMatchCount(userLotto);
        boolean hasBonusNo = userLotto.hasNumber(bonusNo);
        return Rank.getRank(matchCount, hasBonusNo);
    }

    private int getMatchCount(Lotto userLotto) {
        return (int) lotto.getNumbers().stream()
                .filter(number -> userLotto.getNumbers().contains(number))
                .count();
    }

    public static WinningLotto issueWinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNo() {
        return bonusNo;
    }

}
