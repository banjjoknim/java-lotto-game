package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }
}
