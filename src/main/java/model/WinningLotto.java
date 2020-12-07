package model;

import java.util.Arrays;

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
        if (matchCount < 3) {
            return Rank.NONE;
        }
        if (matchCount == 5 && userLotto.hasNumber(bonusNo)) {
            return Rank.SECOND;
        }
        if (matchCount == 5) {
            return Rank.THIRD;
        }
        return getRankByMatchCount(matchCount);
    }

    private int getMatchCount(Lotto userLotto) {
        return (int) userLotto.getNumbers().stream()
                .filter(number -> lotto.getNumbers().contains(number))
                .count();
    }

    private Rank getRankByMatchCount(int matchCount) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getMatchCount() == matchCount)
                .findFirst()
                .orElse(Rank.NONE);
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNo() {
        return bonusNo;
    }
}
