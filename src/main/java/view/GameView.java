package view;

import model.Lotto;
import model.LottoIssuer;
import model.WinningLotto;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class GameView {
    private static final String PLEASE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String PURCHASED = "개를 구매했습니다.";
    private static final String PLEASE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PLEASE_INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String PARTIONING_LINE = "--------";

    private static Scanner sc = new Scanner(System.in);

    public static List<Lotto> purchaseLottos() {
        int money = inputMoney();
        List<Lotto> lottos = LottoIssuer.issueLottoAsMuchPaid(money);
        printPurchased(lottos);
        printLottos(lottos);
        return lottos;
    }

    private static void printPurchased(List<Lotto> lottos) {
        System.out.println(lottos.size() + PURCHASED);
    }

    private static int inputMoney() {
        try {
            printPleaseInputMoney();
            String money = sc.nextLine();
            return Integer.valueOf(money);
        } catch (NumberFormatException e) {
            sc = new Scanner(System.in);
            return inputMoney();
        }
    }

    private static void printPleaseInputMoney() {
        System.out.println(PLEASE_INPUT_MONEY);
    }

    private static void printLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public static WinningLotto inputWinningNumbersAndBonusNo() {
        try {
            List<Integer> winningNumbers = inputWinningNumbers();
            int bonusNo = inputBonusNo();
            return new WinningLotto(new Lotto(winningNumbers), bonusNo);
        } catch (IllegalArgumentException e) {
            return inputWinningNumbersAndBonusNo();
        }
    }

    private static List<Integer> inputWinningNumbers() {
        try {
            printPleaseInputWinningNumbers();
            String input = sc.nextLine();
            return Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(toList());
        } catch (NumberFormatException e) {
            return inputWinningNumbers();
        }
    }

    private static void printPleaseInputWinningNumbers() {
        System.out.println(PLEASE_INPUT_WINNING_NUMBERS);
    }

    private static int inputBonusNo() {
        try {
            printPleaseInputBonusNo();
            int bonusNo = sc.nextInt();
            return bonusNo;
        } catch (InputMismatchException e) {
            return inputBonusNo();
        }
    }

    private static void printPleaseInputBonusNo() {
        System.out.println(PLEASE_INPUT_BONUS_BALL);
    }

}
