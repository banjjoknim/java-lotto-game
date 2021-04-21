package view;

import model.Lotto;
import model.LottoNumber;
import model.WinningLotto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String SEPARATOR = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static BigDecimal inputMoneyAmount() {
        try {
            OutputView.printPleaseInputPurchaseMoney();
            return new BigDecimal(scanner.next());
        } catch (IllegalArgumentException e) {
            return inputMoneyAmount();
        }
    }

    public static List<Integer> inputNumbers() {
        try {
            OutputView.printPleaseInputLastWeekWinningLottoNumber();
            return Arrays.stream(scanner.next().split(SEPARATOR))
                    .map(Integer::valueOf)
                    .collect(toList());
        } catch (IllegalArgumentException e) {
            return inputNumbers();
        }
    }

    public static int inputNumber() {
        OutputView.printPleaseInputBonusBallNumber();
        return scanner.nextInt();
    }
}