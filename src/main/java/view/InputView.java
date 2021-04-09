package view;

import model.Money;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static Money inputMoney() {
        BigDecimal amount = new BigDecimal(scanner.next());
        return new Money(amount);
    }

}
