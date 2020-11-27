package view;

import model.Lotto;
import model.LottoIssuer;

import java.util.List;
import java.util.Scanner;

public class GameView {
    private static final String PLEASE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String PURCHASED = "개를 구매했습니다.";

    private static Scanner sc = new Scanner(System.in);

    public static List<Lotto> purchaseLottos() {
        int money = inputMoney();
        List<Lotto> lottos = LottoIssuer.issueLottoAsMuchPaid(money);
        printPurchased(lottos);
        printLottos(lottos);
        return lottos;
    }

    public static void printPurchased(List<Lotto> lottos) {
        System.out.println(lottos.size() + PURCHASED);
    }

    public static int inputMoney() {
        try {
            printPleaseInputMoney();
            String money = sc.nextLine();
            return Integer.valueOf(money);
        } catch (NumberFormatException e) {
            sc = new Scanner(System.in);
            return inputMoney();
        }
    }

    public static void printPleaseInputMoney() {
        System.out.println(PLEASE_INPUT_MONEY);
    }

    public static void printLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

}
