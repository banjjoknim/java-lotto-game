package model;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int LOTTO_NUMBER_BOUND = 46;

    @Override
    public int generate() {
        return ThreadLocalRandom.current().nextInt(MIN_LOTTO_NUMBER, LOTTO_NUMBER_BOUND);
    }

}
