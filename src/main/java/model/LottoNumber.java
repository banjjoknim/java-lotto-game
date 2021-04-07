package model;

public class LottoNumber {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    private static final String LOTTO_NUMBERS_MUST_BETWEEN_LOTTO_MIN_NUMBER_AND_LOTTO_MAX_NUMBER = "로또 번호는 1부터 45 사이의 숫자로 구성되어야 합니다.";

    private final int number;

    public LottoNumber() {
        int number = createNumber();
        validateNumber(number);
        this.number = number;
    }

    public static void validateNumber(int number) {
        if (LOTTO_MIN_NUMBER > number && number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBERS_MUST_BETWEEN_LOTTO_MIN_NUMBER_AND_LOTTO_MAX_NUMBER);
        }
    }

    private int createNumber() {
        return LOTTO_MIN_NUMBER + (int) (Math.random() * LOTTO_MAX_NUMBER);
    }

    public int getNumber() {
        return number;
    }
}
