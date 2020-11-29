package view;

import model.*;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GameView {
    private static final String PLEASE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String PURCHASED = "개를 구매했습니다.";
    private static final String PLEASE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PLEASE_INPUT_AGAIN = "잘못된 입력입니다. 다시 입력해 주세요.";
    private static final String PLEASE_INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String PARTIONING_LINE = "--------";
    private static final String GET_FIFTH_PLACE_COUNT_IS = "3개 일치 (5000원) - ";
    private static final String GET_FOUTH_PLACE_COUNT_IS = "4개 일치 (50000원) - ";
    private static final String GET_THIRD_PLACE_COUNT_IS = "5개 일치 (1500000원) - ";
    private static final String GET_SECOND_PLACE_COUNT_IS = "5개 일치, 보너스 볼 일치 (30000000원) - ";
    private static final String GET_FIRST_PLACE_COUNT_IS = "6개 일치 (2000000000원) - ";
    private static final String COUNTS = "개";
    private static final String TOTAL_YIELD_IS = "총 수익률은 ";

    private static Scanner sc = new Scanner(System.in);

    public static Lottos purchaseLottos() {
        int money = inputMoney();
        List<Lotto> lottos = getLottosAsMuchPaid(money);
        printPurchased(lottos);
        printLottos(lottos);
        return new Lottos(lottos);
    }

    private static List<Lotto> getLottosAsMuchPaid(int money) {
        try {
            List<Lotto> lottos = Stream.generate(() -> LottoNumbers.createNumbers())
                    .map(Lotto::new)
                    .limit(money / Lotto.PRICE)
                    .collect(toList());
            return lottos;
        } catch (IllegalArgumentException e) {
            return getLottosAsMuchPaid(money);
        }
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
            printPleaseInputAgain();
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
            sc = new Scanner(System.in);
            return inputBonusNo();
        }
    }

    private static void printPleaseInputBonusNo() {
        System.out.println(PLEASE_INPUT_BONUS_BALL);
    }

    private static void printPleaseInputAgain() {
        System.out.println(PLEASE_INPUT_AGAIN);
    }

    public static void printStatistics(WinningLotto winningLotto, Lottos lottos) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(PARTIONING_LINE);

        Map<Rank, Long> statistics = lottos.getStatistics(winningLotto);
        printRankCount(statistics);
        printYield(lottos.calculateYield(winningLotto));
    }

    private static void printRankCount(Map<Rank, Long> statistics) {
        List<String> template = setStatisticsTemplate();
        statistics.remove(Rank.NONE);
        IntStream.range(0, template.size())
                .map(index -> template.size() - 1 - index)
                .mapToObj(index -> template.get(index) + statistics.getOrDefault(Rank.values()[index], 0L) + COUNTS)
                .forEach(System.out::println);
    }

    private static List<String> setStatisticsTemplate() {
        return Stream.of(GET_FIRST_PLACE_COUNT_IS, GET_SECOND_PLACE_COUNT_IS, GET_THIRD_PLACE_COUNT_IS, GET_FOUTH_PLACE_COUNT_IS, GET_FIFTH_PLACE_COUNT_IS)
                .collect(toList());
    }

    private static void printYield(double yield) {
        System.out.println(TOTAL_YIELD_IS + yield + "입니다.");
    }

}
