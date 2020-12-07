package model;

public class WinningLotto {
    private static final String BONUS_NUMBER_IS_DUPLICATE = "보너스 번호가 중복됩니다.";

    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        validateBonusNo(lotto, bonusNo);
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    private void validateBonusNo(Lotto lotto, int bonusNo) {
        if (lotto.getNumbers().contains(bonusNo)) {
            throw new IllegalArgumentException(BONUS_NUMBER_IS_DUPLICATE);
        }
    }

    public Rank match(Lotto userLotto) {
        int matchCount = getMatchCount(userLotto);
        boolean hasBonusNo = userLotto.hasNumber(bonusNo);

        return Rank.getRankByMatchCount(matchCount, hasBonusNo);
    }

    private int getMatchCount(Lotto userLotto) {
        return (int) userLotto.getNumbers().stream()
                .filter(number -> lotto.getNumbers().contains(number))
                .count();
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNo() {
        return bonusNo;
    }
}
